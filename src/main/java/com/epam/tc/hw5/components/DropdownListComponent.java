package com.epam.tc.hw5.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class DropdownListComponent extends AbstractComponent {

    Select select;

    @FindBy(css = ".colors .uui-form-element")
    WebElement dropdownList;

    public DropdownListComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(dropdownList));
    }

    public DropdownListComponent selectOption(String option) {
        select = new Select(dropdownList);
        select.selectByVisibleText(option);
        return this;
    }

    public WebElement getSelectedOption() {
        return select.getFirstSelectedOption();
    }
}
