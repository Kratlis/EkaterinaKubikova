package com.epam.tc.hw3.ex2;

import com.epam.tc.hw3.HomePageTest;
import com.epam.tc.hw3.Utils;
import com.epam.tc.hw3.ex2.components.CheckboxRowComponent;
import com.epam.tc.hw3.ex2.components.LogComponent;
import com.epam.tc.hw3.ex2.pages.DifferentElementsPage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DifferentElementsPageTest extends HomePageTest {
    private DifferentElementsPage differentElementsPage;

    @Test(dataProvider = "selected option, radio, checkboxes",
          dataProviderClass = DifferentElementsPageDataProvider.class)
    public void testDifferentElementsPage(String dropdownOption, String radio, String... checkboxes) {
        SoftAssertions softAssertions = new SoftAssertions();
        // 1. Open test site by URL
        openHomePage();

        // 2. Assert Browser title
        homePageOpenTest(softAssertions, unauthorizedHomePage);

        // 3. Perform login
        login();

        // 4. Assert User name in the left-top side of screen that user is logged
        loginTest(softAssertions);

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
