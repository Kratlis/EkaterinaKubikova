package com.epam.tc.custom;

import static com.epam.jdi.tools.LinqUtils.map;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VegetablesMultiDropdown {

    @FindBy(css = "#vegetables .caret")
    WebElement weatherExpand;
    @FindBy(css = "#vegetables li")
    List<WebElement> weatherList;
    @FindBy(css = "#vegetables button")
    WebElement weatherValue;
    @FindBy(css = "#vegetables ul")
    WebElement weatherIsExpanded;

    private boolean weatherIsExpanded() {
        return weatherIsExpanded.getAttribute("style").equals("display: block;");
    }

    public void check(String... vegetables) {
        if (!weatherIsExpanded()) {
            weatherExpand.click();
        }
        uncheckAll();
        List<String> listNames = map(vegetables, String::trim);
        for (String val : listNames) {
            for (WebElement listOption : weatherList) {
                if (listOption.getText().trim().equals(val)) {
                    listOption.click();
                }
            }
        }
        weatherExpand.click();
    }

    public void uncheckAll() {
        for (WebElement listOption : weatherList) {
            if (listOption.findElement(By.cssSelector("[type=checkbox]")).isSelected()) {
                listOption.click();
            }
        }
    }
}
