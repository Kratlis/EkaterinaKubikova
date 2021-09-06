package com.epam.tc.hw4.ex2;

import com.epam.tc.hw4.HomePageTest;
import com.epam.tc.hw4.components.CheckboxRowComponent;
import com.epam.tc.hw4.components.LogComponent;
import com.epam.tc.hw4.pages.DifferentElementsPage;
import com.epam.tc.hw4.utils.PropertyFileReader;
import com.epam.tc.hw4.utils.Utils;
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
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DifferentElementsPageTest extends HomePageTest {
    private DifferentElementsPage differentElementsPage;

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
        SoftAssertions softAssertions = new SoftAssertions();
        // 1. Open test site by URL
        openHomePage();

        // 2. Assert Browser title
        homePageOpenTest(softAssertions, unauthorizedHomePage);

        // 3. Perform login
        PropertyFileReader.init(Utils.PROPERTY_FILE_PATH);
        String name = PropertyFileReader.readName();
        String password = PropertyFileReader.readPassword();
        login(name, password);

        // 4. Assert Username in the left-top side of screen that user is logged
        String expectedUsername = PropertyFileReader.readUsername();
        loginTest(softAssertions, expectedUsername);

        // 5. Open through the header menu Service -> Different Elements Page
        openDifferentElementsPage();
        pageDifferentElementsOpenTest(softAssertions);

        // 6. Select checkboxes
        selectCheckboxes(checkboxes);
        selectCheckboxesTest(softAssertions, checkboxes);

        // 7. Select radio
        selectRadio(radio);
        selectRadioTest(softAssertions, radio);

        // 8. Select in dropdown
        selectDropdown(dropdownOption);
        selectDropdownTest(softAssertions, dropdownOption);

        // 9. Assert that
        //      - for each checkbox there is an individual log row and value is corresponded
        //        to the status of checkbox
        //      - for radio button there is a log row and value is corresponded to the status
        //        of radio button
        //      - for dropdown there is a log row and value is corresponded to the selected value
        logTest(softAssertions, dropdownOption, radio, checkboxes);
        softAssertions.assertAll();
    }

    @Step("Open Different Elements page from header menu")
    private void openDifferentElementsPage() {
        differentElementsPage = authorizedHomePage.openDifferentElementsPage();
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test Different Elements page opened")
    private void pageDifferentElementsOpenTest(SoftAssertions softAssertion) {
        softAssertion.assertThat(differentElementsPage.url)
                     .isEqualTo(Utils.DIFFERENT_ELEMENTS_PAGE_URL);
        softAssertion.assertThat(differentElementsPage.title)
                     .isEqualTo(Utils.DIFFERENT_ELEMENTS_PAGE_TITLE);
    }

    @Step("Select checkboxes {checkboxes}")
    private void selectCheckboxes(String[] checkboxes) {
        for (String checkbox : checkboxes) {
            differentElementsPage.checkboxRow.selectCheckboxWithTitle(checkbox);
        }
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test checkboxes {checkboxes} are selected")
    private void selectCheckboxesTest(SoftAssertions softAssertion, String[] checkboxes) {
        CheckboxRowComponent checkboxRowComponent = differentElementsPage.checkboxRow;
        List<String> selectedCheckboxesTitles =
            checkboxRowComponent.checkboxes.stream()
                                           .filter(checkboxRowComponent::isCheckboxSelected)
                                           .map(element -> element.getText().trim())
                                           .collect(Collectors.toList());
        softAssertion.assertThat(selectedCheckboxesTitles)
                     .containsExactlyElementsOf(Arrays.asList(checkboxes));
    }

    @Step("Select radio button {radio}")
    private void selectRadio(String radio) {
        differentElementsPage.radioRow.selectRadioButtonWithTitle(radio);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test radio button {radio} is selected")
    private void selectRadioTest(SoftAssertions softAssertion, String radio) {
        WebElement selectedRadio = differentElementsPage.radioRow.getSelectedRadioButton();
        softAssertion.assertThat(selectedRadio.getText())
                     .isEqualTo(radio);
    }

    @Step("Select dropdown list option {dropdownOption}")
    private void selectDropdown(String dropdownOption) {
        differentElementsPage.dropdownList.selectOption(dropdownOption);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test dropdown option {dropdownOption} is selected")
    private void selectDropdownTest(SoftAssertions softAssertion, String dropdownOption) {
        String actualOption = differentElementsPage.dropdownList.getSelectedOption().getText();
        softAssertion.assertThat(actualOption).isEqualTo(dropdownOption);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test dropdown option {dropdownOption} is selected")
    public void logTest(SoftAssertions softAssertion, String dropdownOption, String radio,
                        String[] checkboxes) {
        LogComponent log = differentElementsPage.initLogComponent();

        softAssertion.assertThat(checkboxes)
                     .allMatch(checkbox -> log.containsElementLog(checkbox, Utils.CHECKBOX_LOG_VALUE));
        softAssertion.assertThat(radio)
                     .matches(radioName -> log.containsElementLog(Utils.RADIO_LOG_VALUE, radioName));
        softAssertion.assertThat(dropdownOption)
                     .matches(option -> log.containsElementLog(Utils.DROPDOWN_LOG_VALUE, option));
    }
}
