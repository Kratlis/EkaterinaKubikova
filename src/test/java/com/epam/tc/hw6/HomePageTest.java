package com.epam.tc.hw6;

import com.epam.tc.hw3.pages.AbstractPage;
import com.epam.tc.hw3.pages.AuthorizedHomePage;
import com.epam.tc.hw3.pages.UnauthorizedHomePage;
import com.epam.tc.hw6.utils.Utils;
import com.epam.tc.hw6.utils.WebDriverSingleton;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class HomePageTest {

    protected WebDriverWait wait;
    protected WebDriver driver;
    protected UnauthorizedHomePage unauthorizedHomePage;
    protected AuthorizedHomePage authorizedHomePage;

    @BeforeMethod(description = "Prepare Chrome Web Driver")
    protected void prepareDriver(ITestContext context) {
        driver = Utils.getChromeDriver();
        wait = new WebDriverWait(driver, 10);
        context.setAttribute("WebDriver", driver);
    }

    // 12. Close Browser
    @AfterMethod(description = "Close browser")
    protected void close() {
        WebDriverSingleton.closeDriver();
    }

    @Step("Open Home Page")
    protected void openHomePage() {
        unauthorizedHomePage = (UnauthorizedHomePage) new UnauthorizedHomePage(driver).open();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test opened page {page.title}")
    protected void homePageOpenTest(SoftAssertions softAssertion, AbstractPage page) {
        softAssertion.assertThat(page.url)
                     .isEqualTo(Utils.HOME_PAGE_URL);
        softAssertion.assertThat(page.title)
                     .isEqualTo(Utils.HOME_PAGE_TITLE);
    }

    @Step("Login as {name} : {password}")
    protected void login(String name, String password) {
        unauthorizedHomePage.login(name, password);
        authorizedHomePage = new AuthorizedHomePage(driver, unauthorizedHomePage);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test user logged as {expectedUsername}")
    protected void loginTest(SoftAssertions softAssertion, String expectedUsername) {
        String actualUsername = authorizedHomePage.getUsername();

        softAssertion.assertThat(actualUsername).isEqualTo(expectedUsername);
    }
}
