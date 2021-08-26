package com.epam.tc.hw5.cucumber.steps;

import com.epam.tc.hw3.ex2.components.LogComponent;
import com.epam.tc.hw3.ex2.components.UserTableComponent;
import com.epam.tc.hw5.cucumber.context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;

public class AssertionStep extends AbstractStep {

    @Then("The log row for checkbox {string} and checkbox status {string} "
        + "should be in logs panel on Different Elements page")
    public void testCheckboxSelectedOnDifferentElementsPage(String checkboxTitle, String checkboxStatus) {
        differentElementsPage = TestContext.getInstance().getTestObject("different_elements_page");
        LogComponent log = differentElementsPage.getLog();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(log.containsLog(checkboxTitle, checkboxStatus)).isTrue();
        softAssertions.assertAll();
    }

    @Then("The log row for radio button with value {string} "
        + "should be in logs panel on Different Elements page")
    public void testRadioButtonSelectedOnDifferentElementsPage(String radioTitle) {
        differentElementsPage = TestContext.getInstance().getTestObject("different_elements_page");
        LogComponent log = differentElementsPage.getLog();
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(log.containsLog(radioTitle)).isTrue();
        softAssertions.assertAll();
    }

    @Then("The log row for dropdown option with value {string} should be in logs panel on Different Elements page")
    public void testDropdownOptionSelectedOnDifferentElementsPage(String option) {
        differentElementsPage = TestContext.getInstance().getTestObject("different_elements_page");
        LogComponent log = differentElementsPage.getLog();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(log.containsLog(option)).isTrue();
        softAssertions.assertAll();
    }

    @Then("{string} page should be opened")
    public void testPageOpened(String pageTitle) {
        userTablePage = TestContext.getInstance().getTestObject("user_table_page");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(userTablePage.getTitle()).isEqualTo(pageTitle);
        softAssertions.assertAll();
    }

    @And("{int} Number Type Dropdowns should be displayed on Users Table on User Table Page")
    public void testNumberDropdownsDisplayedOnUserTablePage(int number) {
        userTablePage = TestContext.getInstance().getTestObject("user_table_page");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(userTablePage.getRowNumbers()).hasSize(number);
        softAssertions.assertAll();
    }

    @And("{int} Usernames should be displayed on Users Table on User Table Page")
    public void testUsernamesDisplayedOnUserTablePage(int number) {
        userTablePage = TestContext.getInstance().getTestObject("user_table_page");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(userTablePage.getUsernames()).hasSize(number);
        softAssertions.assertAll();
    }

    @And("{int} Description texts under images should be displayed on Users Table on User Table Page")
    public void testDescriptionTextsDisplayedOnUserTablePage(int number) {
        userTablePage = TestContext.getInstance().getTestObject("user_table_page");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(userTablePage.getDescriptions()).hasSize(number);
        softAssertions.assertAll();
    }

    @And("{int} checkboxes should be displayed on Users Table on User Table Page")
    public void testCheckboxesDisplayedOnUserTablePage(int number) {
        userTablePage = TestContext.getInstance().getTestObject("user_table_page");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(userTablePage.getCheckboxes()).hasSize(number);
        softAssertions.assertAll();
    }

    @And("User table should contain {string} number, {string} user, {string} description")
    public void testUserTableContainsValues(String number, String user, String description) {
        userTablePage = TestContext.getInstance().getTestObject("user_table_page");

        SoftAssertions softAssertions = new SoftAssertions();
        UserTableComponent table = userTablePage.userTableComponent;
        for (int i = 0; i < table.rows.size(); i++) {
            if (table.getNumberInRow(i).equals(number)
                && table.getUsernameInRow(i).equals(user)
                && table.getDescriptionInRow(i).equals(description)) {
                softAssertions.assertThat(true).isTrue();
                softAssertions.assertAll();
            }
        }
        softAssertions.assertThat(false)
                      .as("Table doesn't contain values: " + number + ", " + user + ", " + description + ".");
        softAssertions.assertAll();
    }

    @And("droplist should contain {string} values in column Type for user {string}")
    public void testDroplistContainsValuesForUserOnUserTablePage(String value, String userName) {
        userTablePage = TestContext.getInstance().getTestObject("user_table_page");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(userTablePage.getDropdownsValuesForUser(userName)).contains(value);
        softAssertions.assertAll();
    }

    @Then("1 log row has {string} text in log section")
    public void testLogRowHasTextInLogSection(String logText) {
        userTablePage = TestContext.getInstance().getTestObject("user_table_page");
        LogComponent log = userTablePage.logComponent;

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(log.containsLog(logText)).isTrue();
        softAssertions.assertThat(log.logRows).hasSize(1);
        softAssertions.assertAll();
    }
}
