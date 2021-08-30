package com.epam.tc.hw5.cucumber.steps;

import com.epam.tc.hw5.cucumber.context.TestContext;
import io.cucumber.java.en.When;

public class ActionStep extends AbstractStep {

    @When("I select checkbox {string} in the row with checkboxes on Different Elements page")
    public void selectCheckboxOnDifferentElementsPage(String checkboxTitle) {
        differentElementsPage = TestContext.getInstance().getTestObject("different_elements_page");
        differentElementsPage.checkboxRow.selectCheckboxWithTitle(checkboxTitle);
    }

    @When("I select radio button {string} in the row with radio buttons on Different Elements page")
    public void selectRadioButtonOnDifferentElementsPage(String radioTitle) {
        differentElementsPage = TestContext.getInstance().getTestObject("different_elements_page");
        differentElementsPage.radioRow.selectRadioButtonWithTitle(radioTitle);
    }

    @When("I select option {string} in the dropdown list on Different Elements page")
    public void selectDropdownOptionOnDifferentElementsPage(String option) {
        differentElementsPage = TestContext.getInstance().getTestObject("different_elements_page");
        differentElementsPage.dropdownList.selectOption(option);
    }

    @When("I select {string} checkbox for {string}")
    public void selectCheckboxOnUserTablePage(String checkboxName, String user) {
        userTablePage = TestContext.getInstance().getTestObject("user_table_page");
        if (checkboxName.equalsIgnoreCase(userTablePage.userTableComponent.getCheckboxName())) {
            userTablePage.chooseCheckBoxForUser(user);
        }
    }
}
