package com.epam.tc.hw4.ex2;

import com.epam.tc.hw3.ex2.components.CheckboxRowComponent;
import com.epam.tc.hw3.ex2.components.LogComponent;
import com.epam.tc.hw3.ex2.pages.AuthorizedHomePage;
import com.epam.tc.hw3.ex2.pages.DifferentElementsPage;
import com.epam.tc.hw3.ex2.pages.UnauthorizedHomePage;
import com.epam.tc.hw4.utils.PropertyFileReader;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DifferentElementsPageTest {
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

    @Test(dataProvider = "selected option, radio, checkboxes",
          dataProviderClass = DifferentElementsPageDataProvider.class,
          description = "Test Different Elements Page elements")
    @Description("Test login. Open Different Elements page through header menu. "
        + "Test checkboxes, radioButtons, dropdown list and logs on Different Elements page.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Different Elements Page")
    @Story("Logged user can change checkboxes, radio buttons and select option in dropdown list "
        + "on Different Elements page.")
    public void testDifferentElementsPage(String dropdownOption, String radio, String... checkboxes) {
        SoftAssertions softAssertion = new SoftAssertions();
        // 1. Open test site by URL
        openHomePage();

        // 2. Assert Browser title
        pageOpenTest(softAssertion);

        // 3. Perform login
        PropertyFileReader reader = new PropertyFileReader(com.epam.tc.hw4.ex1.Utils.PROPERTY_FILE_PATH);
        String name = reader.readName();
        String password = reader.readPassword();
        login(name, password);

        // 4. Assert User name in the left-top side of screen that user is logged
        String expectedUsername = reader.readUsername();
        loginTest(softAssertion, expectedUsername);

        // 5. Open through the header menu Service -> Different Elements Page
        openDifferentElementsPage();
        pageDifferentElementsOpenTest(softAssertion);

        // 6. Select checkboxes
        selectCheckboxes(checkboxes);
        selectCheckboxesTest(softAssertion, checkboxes);

        // 7. Select radio
        selectRadio(radio);
        selectRadioTest(softAssertion, radio);

        // 8. Select in dropdown
        selectDropdown(dropdownOption);
        selectDropdownTest(softAssertion, dropdownOption);

        // 9. Assert that
        //      - for each checkbox there is an individual log row and value is corresponded
        //        to the status of checkbox
        //      - for radio button there is a log row and value is corresponded to the status
        //        of radio button
        //      - for dropdown there is a log row and value is corresponded to the selected value
        logTest(softAssertion, dropdownOption, radio, checkboxes);
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

    @Step("Login as {name} : {password}")
    private void login(String name, String password) {
        authorizedHomePage = unauthorizedHomePage.login(name, password);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test user logged as {username}")
    private void loginTest(SoftAssertions softAssertion, String username) {
        String actualUsername = authorizedHomePage.getUsername();
        softAssertion.assertThat(actualUsername).isEqualTo(username);
    }

    @Step("Open Different Elements page from header menu")
    private void openDifferentElementsPage() {
        differentElementsPage = authorizedHomePage.openDifferentElementsPage();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test Different Elements page opened")
    private void pageDifferentElementsOpenTest(SoftAssertions softAssertion) {
        softAssertion.assertThat(differentElementsPage.getUrl())
                     .isEqualTo(Utils.DIFFERENT_ELEMENTS_PAGE_URL);
        softAssertion.assertThat(differentElementsPage.getTitle())
                     .isEqualTo(Utils.DIFFERENT_ELEMENTS_PAGE_TITLE);
    }

    @Step("Select checkboxes {checkboxes}")
    private void selectCheckboxes(String[] checkboxes) {
        differentElementsPage.load();
        for (String checkbox : checkboxes) {
            differentElementsPage.getCheckboxRow()
                                 .selectCheckboxWithTitle(checkbox);
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test checkboxes {checkboxes} are selected")
    private void selectCheckboxesTest(SoftAssertions softAssertion, String[] checkboxes) {
        CheckboxRowComponent checkboxRowComponent = differentElementsPage.getCheckboxRow();
        List<String> selectedCheckboxesTitles =
            checkboxRowComponent.getCheckboxes().stream()
                                .filter(checkboxRowComponent::isCheckboxSelected)
                                .map(element -> element.getText().trim())
                                .collect(Collectors.toList());
        softAssertion.assertThat(selectedCheckboxesTitles)
                     .containsExactlyElementsOf(Arrays.asList(checkboxes));
    }

    @Step("Select radio button {radio}")
    private void selectRadio(String radio) {
        differentElementsPage.getRadioRow().selectRadioButtonWithTitle(radio);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test radio button {radio} is selected")
    private void selectRadioTest(SoftAssertions softAssertion, String radio) {
        WebElement selectedRadio = differentElementsPage.getRadioRow().getSelectedRadioButton();
        softAssertion.assertThat(selectedRadio.getText())
                     .isEqualTo(radio);
    }

    @Step("Select dropdown list option {dropdownOption}")
    private void selectDropdown(String dropdownOption) {
        differentElementsPage.getDropdownList().selectOption(dropdownOption);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test dropdown option {dropdownOption} is selected")
    private void selectDropdownTest(SoftAssertions softAssertion, String dropdownOption) {
        String actualOption = differentElementsPage.getDropdownList().getSelectedOption().getText();
        softAssertion.assertThat(actualOption).isEqualTo(dropdownOption);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test dropdown option {dropdownOption} is selected")
    public void logTest(SoftAssertions softAssertion, String dropdownOption, String radio,
                        String[] checkboxes) {
        LogComponent log = differentElementsPage.getLog();

        softAssertion.assertThat(checkboxes)
                     .allMatch(checkbox -> log.containsLog(checkbox, Utils.CHECKBOX_LOG_VALUE));
        softAssertion.assertThat(radio)
                     .matches(radioName -> log.containsLog(Utils.RADIO_LOG_VALUE, radioName));
        softAssertion.assertThat(dropdownOption)
                     .matches(option -> log.containsLog(Utils.DROPDOWN_LOG_VALUE, option));
    }
}
