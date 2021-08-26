package com.epam.tc.hw5.cucumber.steps;

import com.epam.tc.hw3.ex2.components.ServiceMenuComponent;
import com.epam.tc.hw3.ex2.pages.UnauthorizedHomePage;
import com.epam.tc.hw5.cucumber.context.TestContext;
import com.epam.tc.hw5.utils.PropertyFileReader;
import com.epam.tc.hw5.utils.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class GivenStep extends AbstractStep {

    @Given("I open JDI GitHub site")
    public void openHomePage() {
        WebDriver driver = TestContext.getInstance().getTestObject("web_driver");
        unauthorizedHomePage = new UnauthorizedHomePage(driver);
    }

    @And("I login as user {string}")
    public void login(String username) {
        PropertyFileReader reader = new PropertyFileReader(Utils.PROPERTY_FILE_PATH);
        if (reader.readUsername().equalsIgnoreCase(username)) {
            authorizedHomePage = unauthorizedHomePage
                .login(reader.readName(), reader.readPassword());
        }
    }

    @Given("I click on {string} button in Header")
    public void clickMenuItemInHeader(String menuItem) {
        if (menuItem.equalsIgnoreCase(Utils.SERVICE_MENU_ITEM)) {
            ServiceMenuComponent serviceMenuComponent = authorizedHomePage.openServiceMenuComponent();
            TestContext.getInstance().addTestObject("service_menu_component", serviceMenuComponent);
        }
    }

    @And("I click on {string} button in Service dropdown")
    public void clickButtonInServiceDropdown(String button) {
        ServiceMenuComponent serviceMenuComponent =
            TestContext.getInstance().getTestObject("service_menu_component");
        if (button.equalsIgnoreCase(Utils.DIFFERENT_ELEMENTS_SERVICE_MENU_ITEM)) {
            differentElementsPage = serviceMenuComponent.chooseDifferentElements().load();
            TestContext.getInstance().addTestObject("different_elements_page", differentElementsPage);
        }
        if (button.equalsIgnoreCase(Utils.USER_TABLE_SERVICE_MENU_ITEM)) {
            userTablePage = serviceMenuComponent.chooseUserTable();
            TestContext.getInstance().addTestObject("user_table_page", userTablePage);
        }
    }
}
