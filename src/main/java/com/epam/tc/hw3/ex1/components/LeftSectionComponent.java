package com.epam.tc.hw3.ex1.components;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LeftSectionComponent extends AbstractComponent {
    @FindBy(css = ".left > li")
    List<WebElement> leftSectionItems;

    public LeftSectionComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(leftSectionItems));
    }

    public String getSectionItemTitle(WebElement sectionItem) {
        return new LeftSectionItemElement(sectionItem, driver).getName();
    }

    public List<WebElement> getLeftSectionItems() {
        return leftSectionItems;
    }

    private static class LeftSectionItemElement {
        WebElement webElement;

        @FindBy(css = ".left li span")
        WebElement name;

        LeftSectionItemElement(WebElement element, WebDriver driver) {
            webElement = element;
            PageFactory.initElements(driver, this);
        }

        public String getName() {
            return name.getText();
        }
    }
}
