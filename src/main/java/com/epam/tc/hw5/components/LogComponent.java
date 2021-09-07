package com.epam.tc.hw5.components;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogComponent extends AbstractComponent {

    @FindBy(css = ".logs li")
    public List<WebElement> logRows;

    public LogComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean containsLog(String elementName, String expectedValue) {
        wait.until(ExpectedConditions.visibilityOfAllElements(logRows));
        for (WebElement row : logRows) {
            if (row.getText().matches(".*" + elementName + ".*" + expectedValue)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsLog(String expectedValue) {
        wait.until(ExpectedConditions.visibilityOfAllElements(logRows));
        for (WebElement row : logRows) {
            if (row.getText().matches(".*" + expectedValue + ".*")) {
                return true;
            }
        }
        return false;
    }
}
