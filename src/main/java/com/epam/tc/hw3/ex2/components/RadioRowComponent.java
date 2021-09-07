package com.epam.tc.hw3.ex2.components;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RadioRowComponent extends AbstractComponent {

    @FindBy(className = "label-radio")
    List<WebElement> radioButtons;

    public RadioRowComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(radioButtons));
    }

    public RadioRowComponent selectRadioButtonWithTitle(String title) {
        for (WebElement radio : radioButtons) {
            if (radio.getText().contains(title)) {
                radio.click();
                break;
            }
        }
        return this;
    }

    public WebElement getSelectedRadioButton() {
        for (WebElement radio : radioButtons) {
            if (isRadioButtonSelected(radio)) {
                return radio;
            }
        }
        return null;
    }

    private boolean isRadioButtonSelected(WebElement radio) {
        return radio.findElement(By.tagName("input")).isSelected();
    }
}
