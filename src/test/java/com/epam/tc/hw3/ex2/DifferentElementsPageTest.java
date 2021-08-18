package com.epam.tc.hw3.ex2;

import com.epam.tc.hw3.ex2.components.CheckboxRowComponent;
import com.epam.tc.hw3.ex2.components.LogComponent;
import com.epam.tc.hw3.ex2.pages.AuthorizedHomePage;
import com.epam.tc.hw3.ex2.pages.DifferentElementsPage;
import com.epam.tc.hw3.ex2.pages.UnauthorizedHomePage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DifferentElementsPageTest {
    private WebDriver driver;
    private UnauthorizedHomePage unauthorizedHomePage;
    private AuthorizedHomePage authorizedHomePage;
    private DifferentElementsPage differentElementsPage;

    @BeforeMethod
    public void prepareDriver() {
        driver = Utils.getChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
    }

    // 12. Close Browser
    @AfterMethod
    private void close() {
        driver.quit();
    }

    @Test(dataProvider = "selected option, radio, checkboxes",
          dataProviderClass = DifferentElementsPageDataProvider.class)
    public void testHomePage(String dropdownOption, String radio, String... checkboxes) {
        SoftAssertions softAssertion = new SoftAssertions();
        // 1. Open test site by URL
        openHomePage();

        // 2. Assert Browser title
        pageOpenTest(softAssertion);

        // 3. Perform login
        login();

        // 4. Assert User name in the left-top side of screen that user is logged
        loginTest(softAssertion);

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

    private void openHomePage() {
        unauthorizedHomePage = new UnauthorizedHomePage(driver);
    }

    private void pageOpenTest(SoftAssertions softAssertion) {
        softAssertion.assertThat(unauthorizedHomePage.getUrl())
                     .isEqualTo(Utils.HOME_PAGE_URL);
        softAssertion.assertThat(unauthorizedHomePage.getTitle())
                     .isEqualTo(Utils.HOME_PAGE_TITLE);
    }

    private void login() {
        PropertyFileReader reader = new PropertyFileReader(Utils.PROPERTY_FILE_PATH);
        String name = reader.readName();
        String password = reader.readPassword();
        authorizedHomePage = unauthorizedHomePage.login(name, password);
    }

    private void loginTest(SoftAssertions softAssertion) {
        PropertyFileReader reader = new PropertyFileReader(Utils.PROPERTY_FILE_PATH);
        String expectedUsername = reader.readUsername();
        String actualUsername = authorizedHomePage.getUsername();
        softAssertion.assertThat(actualUsername).isEqualTo(expectedUsername);
    }

    private void openDifferentElementsPage() {
        differentElementsPage = authorizedHomePage.openDifferentElementsPage();
    }

    private void pageDifferentElementsOpenTest(SoftAssertions softAssertion) {
        softAssertion.assertThat(differentElementsPage.getUrl())
                     .isEqualTo(Utils.DIFFERENT_ELEMENTS_PAGE_URL);
        softAssertion.assertThat(differentElementsPage.getTitle())
                     .isEqualTo(Utils.DIFFERENT_ELEMENTS_PAGE_TITLE);
    }

    private void selectCheckboxes(String[] checkboxes) {
        differentElementsPage.load();
        for (String checkbox : checkboxes) {
            differentElementsPage.getCheckboxRow()
                                 .selectCheckboxWithTitle(checkbox);
        }
    }

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

    private void selectRadio(String radio) {
        differentElementsPage.getRadioRow().selectRadioButtonWithTitle(radio);
    }

    private void selectRadioTest(SoftAssertions softAssertion, String radio) {
        WebElement selectedRadio = differentElementsPage.getRadioRow().getSelectedRadioButton();
        softAssertion.assertThat(selectedRadio.getText())
                     .isEqualTo(radio);
    }

    private void selectDropdown(String dropdownOption) {
        differentElementsPage.getDropdownList().selectOption(dropdownOption);
    }

    private void selectDropdownTest(SoftAssertions softAssertion, String dropdownOption) {
        String actualOption = differentElementsPage.getDropdownList().getSelectedOption().getText();
        softAssertion.assertThat(actualOption).isEqualTo(dropdownOption);
    }

    public void logTest(SoftAssertions softAssertion, String dropdownOption, String radio,
                        String[] checkboxes) {
        LogComponent log = differentElementsPage.getLog();

        softAssertion.assertThat(checkboxes)
                     .allMatch(checkbox -> log.containsElementLog(checkbox, Utils.CHECKBOX_LOG_VALUE));
        softAssertion.assertThat(radio)
                     .matches(radioName -> log.containsElementLog(Utils.RADIO_LOG_VALUE, radioName));
        softAssertion.assertThat(dropdownOption)
                     .matches(option -> log.containsElementLog(Utils.DROPDOWN_LOG_VALUE, option));
    }
}
