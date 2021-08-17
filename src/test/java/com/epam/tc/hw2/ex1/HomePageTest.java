package com.epam.tc.hw2.ex1;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest {

    private WebDriverWait wait;
    private WebDriver driver;

    @BeforeMethod
    public void prepareDriver() {
        driver = Utils.getChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    // 12. Close Browser
    @AfterMethod
    private void close() {
        driver.quit();
    }

    @Test
    public void testHomePage() {
        SoftAssertions softAssert = new SoftAssertions();

        // 1. Open test site by URL
        openSite();

        // 2. Assert Browser title
        pageOpenTest(softAssert);

        // 3. Perform login
        login();

        // 4. Assert Username is logged
        loginTest(softAssert);

        // 5. Assert that there are 4 items on the header section are displayed,
        //    and they have proper texts
        headerSectionItemsTest(softAssert);

        // 6. Assert that there are 4 images on the Index Page,
        //    and they are displayed
        indexPageImagesTest(softAssert);

        // 7. Assert that there are 4 texts on the Index Page under icons,
        //    and they have proper text
        indexPageTextsTest(softAssert);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        // 10. Switch to original window back
        iframeTest(softAssert);

        // 11. Assert that there are 5 items in the Left Section are displayed,
        //     and they have proper text
        leftSectionItemsTest(softAssert);
        softAssert.assertAll();
    }

    private void openSite() {
        driver.navigate().to(Utils.HOME_PAGE_URL);
    }

    private void pageOpenTest(SoftAssertions softAssert) {
        softAssert.assertThat(driver.getCurrentUrl()).isEqualTo(Utils.HOME_PAGE_URL);
        softAssert.assertThat(driver.getTitle()).isEqualTo(Utils.HOME_PAGE_TITLE);
    }

    private void login() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.className("profile-photo")
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

    private void loginTest(SoftAssertions softAssert) {
        String actualUsername = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("user-name"))
        ).getText();
        softAssert.assertThat(actualUsername).isEqualTo(Utils.USERNAME);
    }

    private void headerSectionItemsTest(SoftAssertions softAssert) {
        List<WebElement> menuButtons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.cssSelector(".nav > li")
        ));

        int num = menuButtons.size();
        softAssert.assertThat(num).isEqualTo(Utils.menuButtons.size());

        List<String> menuButtonsTitles = menuButtons
            .stream().map(WebElement::getText).collect(Collectors.toList());
        softAssert.assertThat(menuButtonsTitles)
                  .containsExactlyElementsOf(Utils.menuButtons);
    }

    private void indexPageImagesTest(SoftAssertions softAssert) {
        List<WebElement> images = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
            By.className("benefit-icon")
        ));
        softAssert.assertThat(images).hasSize(Utils.IMAGES_NUMBER);

        softAssert.assertThat(images).allMatch(WebElement::isDisplayed);
    }

    private void indexPageTextsTest(SoftAssertions softAssert) {
        List<String> texts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.className("benefit-txt")
        )).stream().map(WebElement::getText).collect(Collectors.toList());
        softAssert.assertThat(texts).hasSize(Utils.imagesTexts.size());

        softAssert.assertThat(texts)
                  .containsExactlyElementsOf(Utils.imagesTexts);
    }

    private void iframeTest(SoftAssertions softAssert) {
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
                break;
            } catch (NoSuchElementException ignored) {
                continue;
            } finally {
                driver.switchTo().defaultContent();
            }
        }
        softAssert.assertThat(button).isNotNull();
    }

    private void leftSectionItemsTest(SoftAssertions softAssert) {
        List<WebElement> leftSectionItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.cssSelector(".left > li")
        ));
        softAssert.assertThat(leftSectionItems).hasSize(Utils.leftSectionItemsTexts.size());
        softAssert.assertThat(leftSectionItems)
                  .allMatch(WebElement::isDisplayed);
        List<String> leftSectionItemsTexts =
            leftSectionItems.stream().map(WebElement::getText).collect(Collectors.toList());
        softAssert.assertThat(leftSectionItemsTexts)
            .containsExactlyElementsOf(Utils.leftSectionItemsTexts);
    }
}
