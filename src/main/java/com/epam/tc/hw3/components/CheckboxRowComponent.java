package com.epam.tc.hw3.components;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckboxRowComponent extends AbstractComponent {

    @FindBy(className = "label-checkbox")
    public List<WebElement> checkboxes;

    public CheckboxRowComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(checkboxes));
    }

    public CheckboxRowComponent selectCheckboxWithTitle(String title) {
        for (WebElement checkbox : checkboxes) {
            if (checkbox.getText().contains(title)) {
                checkbox.click();
            }
        }
        return this;
    }

    public boolean isCheckboxSelected(WebElement checkbox) {
        return checkbox.findElement(By.tagName("input")).isSelected();
    }
}
