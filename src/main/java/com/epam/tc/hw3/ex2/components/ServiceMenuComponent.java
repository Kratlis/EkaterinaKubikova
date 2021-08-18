package com.epam.tc.hw3.ex2.components;

import com.epam.tc.hw3.ex2.pages.DifferentElementsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ServiceMenuComponent extends AbstractComponent {

    @FindBy(xpath = "//*[text()='Different elements']")
    WebElement differentElementsItem;

    protected ServiceMenuComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.elementToBeClickable(differentElementsItem));
    }

    public DifferentElementsPage choseDifferentElements() {
        differentElementsItem.click();
        return new DifferentElementsPage(driver);
    }
}
