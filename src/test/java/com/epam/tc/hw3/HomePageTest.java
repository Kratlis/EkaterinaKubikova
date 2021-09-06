package com.epam.tc.hw3;

import com.epam.tc.hw3.ex1.pages.AbstractPage;
import com.epam.tc.hw3.ex1.pages.AuthorizedHomePage;
import com.epam.tc.hw3.ex1.pages.UnauthorizedHomePage;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class HomePageTest {

    protected WebDriverWait wait;
    protected WebDriver driver;
    protected UnauthorizedHomePage unauthorizedHomePage;
    protected AuthorizedHomePage authorizedHomePage;

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
        unauthorizedHomePage = new UnauthorizedHomePage(driver);
    }

    protected void homePageOpenTest(SoftAssertions softAssertion, AbstractPage page) {
        softAssertion.assertThat(page.getUrl())
                     .isEqualTo(Utils.HOME_PAGE_URL);
        softAssertion.assertThat(page.getTitle())
                     .isEqualTo(Utils.HOME_PAGE_TITLE);
    }

    protected void login() {
        PropertyFileReader reader = new PropertyFileReader(Utils.PROPERTY_FILE_PATH);
        String name = reader.readName();
        String password = reader.readPassword();
        unauthorizedHomePage.login(name, password);
        authorizedHomePage = new AuthorizedHomePage(driver, unauthorizedHomePage);
    }

    protected void loginTest(SoftAssertions softAssertion) {
        String expectedUsername = new PropertyFileReader(Utils.PROPERTY_FILE_PATH).readUsername();
        String actualUsername = authorizedHomePage.getUsername();

        softAssertion.assertThat(actualUsername).isEqualTo(expectedUsername);
    }
}
