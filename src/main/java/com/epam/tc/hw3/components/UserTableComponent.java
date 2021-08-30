package com.epam.tc.hw3.components;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserTableComponent extends AbstractComponent {

    @FindBy(css = "tbody > tr")
    public List<WebElement> rows;

    @FindBy(xpath = "//tr[1]/th")
    public List<WebElement> columns;

    @FindBy(xpath = "//tr/td[1]")
    public List<WebElement> numberCells;

    @FindBy(tagName = "select")
    public List<WebElement> typeDropdowns;

    @FindBy(xpath = "//tr/td[3]")
    public List<WebElement> usernameCells;

    @FindBy(className = "user-descr")
    public List<WebElement> descriptionCells;

    @FindBy(css = "input[type='checkbox']")
    public List<WebElement> checkboxes;

    @FindBy(css = ".user-descr > label")
    public WebElement checkboxName;

    public UserTableComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(rows));
        wait.until(ExpectedConditions.visibilityOfAllElements(columns));
        wait.until(ExpectedConditions.visibilityOfAllElements(numberCells));
        wait.until(ExpectedConditions.visibilityOfAllElements(typeDropdowns));
        wait.until(ExpectedConditions.visibilityOfAllElements(usernameCells));
        wait.until(ExpectedConditions.visibilityOfAllElements(descriptionCells));
        wait.until(ExpectedConditions.visibilityOfAllElements(checkboxes));
    }

    public String getCheckboxName() {
        return checkboxName.getText();
    }

    public String getNumberInRow(int rowNum) {
        return numberCells.get(rowNum).getText();
    }

    public String getUsernameInRow(int rowNum) {
        return usernameCells.get(rowNum).getText();
    }

    public String getDescriptionInRow(int rowNum) {
        return descriptionCells.get(rowNum).getText().split("\n")[0];
    }

    public List<String> getDescriptions() {
        return descriptionCells.stream()
                               .map(element -> element.getText().split("\n")[0])
                               .collect(Collectors.toList());
    }
}
