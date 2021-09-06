package com.epam.tc.hw5.components;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LeftSectionComponent extends AbstractComponent {
    @FindBy(css = ".left > li")
    public List<WebElement> leftSectionItems;

    public LeftSectionComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(leftSectionItems));
    }
}
