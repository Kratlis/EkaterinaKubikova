package com.epam.tc.hw2.ex2;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DifferentElementsPageTest {
    private WebDriverWait wait;
    private WebDriver driver;

    @BeforeMethod
    public void prepareDriver() {
        driver = Utils.getChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    // 12. Close Browser
    @AfterMethod
    private void close() {
        driver.quit();
    }

    @Test
    public void testHomePage() {
        // 1. Open test site by URL
        openSite();

        // 2. Assert Browser title
        pageOpenTest();

        // 3. Perform login
        login();

        // 4. Assert User name in the left-top side of screen that user is logged
        loginTest();

        // 5. Open through the header menu Service -> Different Elements Page
        openDifferentElementsPage();
        pageDifferentElementsOpenTest();

        // 6. Select checkboxes
        selectCheckboxes();
        selectCheckboxesTest();

        // 7. Select radio
        selectRadio();
        selectRadioTest();

        // 8. Select in dropdown
        selectDropdown();
        selectDropdownTest();

        // 9. Assert that
        //      - for each checkbox there is an individual log row and value is corresponded
        //        to the status of checkbox
        //      - for radio button there is a log row and value is corresponded to the status
        //        of radio button
        //      - for dropdown there is a log row and value is corresponded to the selected value
        logTest();
    }

    private void openSite() {
        driver.navigate().to(Utils.HOME_PAGE_URL);
    }

    private void pageOpenTest() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(driver.getCurrentUrl(), Utils.HOME_PAGE_URL);
        softAssert.assertEquals(driver.getTitle(), Utils.HOME_PAGE_TITLE);
        softAssert.assertAll();
    }

    private void login() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[@href='#']")
        ));
        loginButton.click();
        WebElement loginField = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("name")
        ));
        loginField.sendKeys(Utils.LOGIN);
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("password")
        ));
        passwordField.sendKeys(Utils.PASSWORD);
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.id("login-button")
        ));
        submitButton.click();
    }

    private void loginTest() {
        String actualUsername = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("user-name"))
        ).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUsername, Utils.USERNAME);
        softAssert.assertAll();
    }

    private void openDifferentElementsPage() {
        WebElement service = wait.until(ExpectedConditions.elementToBeClickable(
            By.className("dropdown")
        ));
        service.click();
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[text()='Different elements']")
        )).click();
    }

    private void pageDifferentElementsOpenTest() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(driver.getCurrentUrl(), Utils.DIFFERENT_ELEMENTS_PAGE_URL);
        softAssert.assertEquals(driver.getTitle(), Utils.DIFFERENT_ELEMENTS_PAGE_TITLE);
        softAssert.assertAll();
    }

    private void selectCheckboxes() {
        for (int i = 0; i < Utils.checkboxesNames.size(); i++) {
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(., '" + Utils.checkboxesNames.get(i) + "')]")
            ));
            checkbox.click();
        }
    }

    private void selectCheckboxesTest() {
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < Utils.checkboxesNames.size(); i++) {
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(., '" + Utils.checkboxesNames.get(i) + "')]")
            ));
            softAssert.assertTrue(checkbox.findElement(By.tagName("input")).isSelected());
        }
        softAssert.assertAll();
    }

    private void selectRadio() {
        WebElement radio = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[contains(., '" + Utils.RADIO_NAME + "')]")
        ));
        radio.click();
    }

    private void selectRadioTest() {
        SoftAssert softAssert = new SoftAssert();
        WebElement radio = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[contains(., '" + Utils.RADIO_NAME + "')]")
        ));
        softAssert.assertTrue(radio.findElement(By.tagName("input")).isSelected());
        softAssert.assertAll();
    }

    private void selectDropdown() {
        Select colors = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".colors .uui-form-element")
        )));
        colors.selectByVisibleText(Utils.DROPDOWN_OPTION_NAME);
    }

    private void selectDropdownTest() {
        SoftAssert softAssert = new SoftAssert();

        Select colors = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".colors .uui-form-element")
        )));
        String actualOption = colors.getFirstSelectedOption().getText();
        softAssert.assertEquals(actualOption, Utils.DROPDOWN_OPTION_NAME);
        softAssert.assertAll();
    }

    public void logTest() {
        SoftAssert softAssert = new SoftAssert();
        WebElement logPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.className("logs")
        ));
        for (int i = 0; i < Utils.checkboxesNames.size(); i++) {
            WebElement checkboxLogRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(@class,'logs')]/li[contains(., '" + Utils.checkboxesNames.get(i) + "')]")
            ));
            softAssert.assertTrue(checkboxLogRow.getText().matches(Utils.checkboxesLogs.get(i)));
        }
        WebElement radioLogRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(@class,'logs')]/li[contains(., '" + Utils.RADIO_NAME + "')]")
        ));
        softAssert.assertTrue(radioLogRow.getText().matches(Utils.RADIO_LOG));
        WebElement dropdownLogRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(@class,'logs')]/li[contains(., '" + Utils.DROPDOWN_OPTION_NAME + "')]")
        ));
        softAssert.assertTrue(dropdownLogRow.getText().matches(Utils.DROPDOWN_OPTION_LOG));

        softAssert.assertAll();
    }
}
