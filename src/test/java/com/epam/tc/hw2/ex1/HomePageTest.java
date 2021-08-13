package com.epam.tc.hw2.ex1;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageTest {

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

        // 4. Assert Username is logged
        loginTest();

        // 5. Assert that there are 4 items on the header section are displayed,
        //    and they have proper texts
        headerSectionItemsTest();

        // 6. Assert that there are 4 images on the Index Page,
        //    and they are displayed
        indexPageImagesTest();

        // 7. Assert that there are 4 texts on the Index Page under icons,
        //    and they have proper text
        indexPageTextsTest();

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        // 10. Switch to original window back
        iframeTest();

        // 11. Assert that there are 5 items in the Left Section are displayed,
        //     and they have proper text
        leftSectionItemsTest();
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

    private void headerSectionItemsTest() {
        SoftAssert softAssert = new SoftAssert();

        List<WebElement> menuButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.cssSelector(".nav > li")
        ));

        int num = menuButtons.size();
        softAssert.assertEquals(num, Utils.MENU_BUTTONS_NUMBER);

        for (int i = 0; i < menuButtons.size(); i++) {
            softAssert.assertEquals(menuButtons.get(i).getText(),
                Utils.menuButtons.get(i));
        }
        softAssert.assertAll();
    }

    private void indexPageImagesTest() {
        SoftAssert softAssert = new SoftAssert();

        List<WebElement> images = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.className("benefit-icon")
        ));
        int imagesNumber = images.size();
        softAssert.assertEquals(imagesNumber, Utils.IMAGES_NUMBER);

        for (WebElement image : images) {
            softAssert.assertTrue(image.isDisplayed());
        }

        softAssert.assertAll();
    }

    private void indexPageTextsTest() {
        SoftAssert softAssert = new SoftAssert();

        List<WebElement> texts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.className("benefit-txt")
        ));
        int textsNumber = texts.size();
        softAssert.assertEquals(textsNumber, Utils.TEXTS_NUMBER);

        for (int i = 0; i < Utils.TEXTS_NUMBER; i++) {
            String actualImageText = wait.until(ExpectedConditions.presenceOfElementLocated(
                Utils.imagesXpathSelectors.get(i)
            )).getText();
            String expectedImageText = Utils.imagesDescriptions.get(i);
            softAssert.assertEquals(actualImageText, expectedImageText);
        }
        softAssert.assertAll();
    }

    private void iframeTest() {
        WebElement button = null;
        for (int i = 0; i < Utils.FRAME_NUMBER; i++) {
            WebElement iframe = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.tagName("iframe")
            )).get(i);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
            try {
                button = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[value='Frame Button']")
                ));
            } catch (NoSuchElementException e) {
                driver.switchTo().defaultContent();
                continue;
            }
            driver.switchTo().defaultContent();
            break;
        }
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(button);
        softAssert.assertAll();
    }

    private void leftSectionItemsTest() {
        SoftAssert softAssert = new SoftAssert();
        WebElement leftSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.className("left")
        ));
        List<WebElement> leftSectionItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.cssSelector(".left > li")
        ));
        softAssert.assertEquals(leftSectionItems.size(), Utils.LEFT_SECTION_ITEMS_NUM);
        for (int i = 0; i < leftSectionItems.size(); i++) {
            WebElement item = leftSectionItems.get(i);
            softAssert.assertTrue(item.isDisplayed());
            String itemText = wait.until(ExpectedConditions.visibilityOf(
                item.findElement(By.tagName("span"))
            )).getText();
            softAssert.assertEquals(itemText, Utils.leftSectionItemsTexts.get(i));
        }
        softAssert.assertAll();
    }
}
