package com.epam.tc.hw5;

import com.epam.tc.hw3.ex2.components.LogComponent;
import com.epam.tc.hw3.ex2.pages.AuthorizedHomePage;
import com.epam.tc.hw3.ex2.pages.DifferentElementsPage;
import com.epam.tc.hw3.ex2.pages.UnauthorizedHomePage;
import com.epam.tc.hw3.ex2.pages.UserTablePage;
import com.epam.tc.hw5.cucumber.context.TestContext;
import com.epam.tc.hw5.utils.PropertyFileReader;
import com.epam.tc.hw5.utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserTest {
    UserTablePage userTablePage;
    private WebDriver driver;
    private UnauthorizedHomePage unauthorizedHomePage;
    private AuthorizedHomePage authorizedHomePage;
    private DifferentElementsPage differentElementsPage;

    @BeforeMethod(description = "Prepare Chrome Web Driver")
    public void prepareDriver() {
        driver = Utils.getChromeDriver();
    }

    // 12. Close Browser
    @AfterMethod(description = "Close browser")
    private void close() {
        driver.quit();
    }

    @Test(
          description = "Test Different Elements Page elements")
    @Description("Test login. Open Different Elements page through header menu. "
        + "Test checkboxes, radioButtons, dropdown list and logs on Different Elements page.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Different Elements Page")
    @Story("Logged user can change checkboxes, radio buttons and select option in dropdown list "
        + "on Different Elements page.")
    public void testDifferentElementsPage(String dropdownOption, String radio, String... checkboxes) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        TestContext.getInstance().addTestObject("web_driver", driver);

        WebDriver driver = TestContext.getInstance().getTestObject("web_driver");
        unauthorizedHomePage = new UnauthorizedHomePage(driver);

        String username = "roman iovlev";
        PropertyFileReader reader = new PropertyFileReader(Utils.PROPERTY_FILE_PATH);
        if (reader.readUsername().equalsIgnoreCase(username)) {
            authorizedHomePage = unauthorizedHomePage
                .login(reader.readName(), reader.readPassword());
        }

        SoftAssertions softAssertion = new SoftAssertions();

        // 4. Assert User name in the left-top side of screen that user is logged
        String expectedUsername = reader.readUsername();
        loginTest(softAssertion, expectedUsername);

        userTablePage = authorizedHomePage.openServiceMenuComponent().chooseUserTable();

        String checkboxName = "vip";
        String user = "Sergey Ivan";
        if (checkboxName.equalsIgnoreCase(userTablePage.userTableComponent.getCheckboxName())) {
            userTablePage.chooseCheckBoxForUser(user);
        }

        LogComponent log = userTablePage.logComponent;
        String logText = "Vip: condition changed to true";
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(log.containsLog(logText)).isTrue();
        softAssertions.assertThat(log.logRows).hasSize(1);
        softAssertion.assertAll();
    }

    @Step("Open Home Page")
    private void openHomePage() {
        unauthorizedHomePage = new UnauthorizedHomePage(driver);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test Home page opened")
    private void pageOpenTest(SoftAssertions softAssertion) {
        softAssertion.assertThat(unauthorizedHomePage.getUrl())
                     .isEqualTo(Utils.HOME_PAGE_URL);
        softAssertion.assertThat(unauthorizedHomePage.getTitle())
                     .isEqualTo(Utils.HOME_PAGE_TITLE);
    }

    private void login(String name, String password) {
        authorizedHomePage = unauthorizedHomePage.login(name, password);
    }

    private void loginTest(SoftAssertions softAssertion, String username) {
        String actualUsername = authorizedHomePage.getUsername();
        softAssertion.assertThat(actualUsername).isEqualTo(username);
    }
}
