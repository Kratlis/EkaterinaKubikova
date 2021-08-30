package com.epam.tc.hw3.components;

import com.epam.tc.hw3.pages.DifferentElementsPage;
import com.epam.tc.hw3.pages.UserTablePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ServiceMenuComponent extends AbstractComponent {

    @FindBy(xpath = "//*[text()='Different elements']")
    WebElement differentElementsItem;

    @FindBy(xpath = "//*[text()='User Table ']")
    WebElement userTableItem;

    public ServiceMenuComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.elementToBeClickable(differentElementsItem));
    }

    public DifferentElementsPage chooseDifferentElements() {
        differentElementsItem.click();
        return new DifferentElementsPage(driver);
    }

    public UserTablePage chooseUserTable() {
        userTableItem.click();
        return new UserTablePage(driver);
    }
}
