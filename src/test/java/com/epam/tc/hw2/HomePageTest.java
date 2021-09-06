package com.epam.tc.hw2;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class HomePageTest {

    protected WebDriverWait wait;
    protected WebDriver driver;

    @BeforeMethod
    protected void prepareDriver() {
        driver = Utils.getChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    // 12. Close Browser
    @AfterMethod
    protected void close() {
        driver.quit();
    }

    protected void openHomePage() {
        driver.navigate().to(Utils.HOME_PAGE_URL);
    }

    protected void homePageOpenTest(SoftAssertions softAssert) {
        softAssert.assertThat(driver.getCurrentUrl()).isEqualTo(Utils.HOME_PAGE_URL);
        softAssert.assertThat(driver.getTitle()).isEqualTo(Utils.HOME_PAGE_TITLE);
    }

    protected void login() {
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

    protected void loginTest(SoftAssertions softAssert) {
        String actualUsername = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.id("user-name"))
        ).getText();
        softAssert.assertThat(actualUsername).isEqualTo(Utils.USERNAME);
    }
}
