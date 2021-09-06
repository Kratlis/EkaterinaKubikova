package com.epam.tc.hw6.components;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogComponent extends AbstractComponent {

    @FindBy(css = ".logs li")
    List<WebElement> logRows;

    public LogComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(logRows));
    }

    public boolean containsElementLog(String elementName, String expectedValue) {
        for (WebElement row : logRows) {
            if (row.getText().matches(".*" + elementName + ".*" + expectedValue)) {
                return true;
            }
        }
        return false;
    }
}
