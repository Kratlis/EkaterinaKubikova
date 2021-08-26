package com.epam.tc.hw3.ex2.pages;

import com.epam.tc.hw3.ex2.components.LogComponent;
import com.epam.tc.hw3.ex2.components.UserTableComponent;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class UserTablePage extends AbstractPage {
    static final String USER_TABLE_PAGE_URL =
        "https://jdi-testing.github.io/jdi-light/user-table.html";
    public UserTableComponent userTableComponent;
    public LogComponent logComponent;

    @FindBy(id = "ivan")
    private WebElement checkboxSergeyIvan;

    @FindBy(className = "info-panel-section")
    private List<WebElement> logSection;

    @FindBy(css = ".panel-body-list.logs")
    private List<WebElement> logRows;

    public UserTablePage(WebDriver driver) {
        super(driver);
        open();
        PageFactory.initElements(driver, this);
        userTableComponent = new UserTableComponent(driver);
        logComponent = new LogComponent(driver);
        wait.until(ExpectedConditions.visibilityOf(checkboxSergeyIvan));
        wait.until(ExpectedConditions.visibilityOfAllElements(logSection));
        wait.until(ExpectedConditions.visibilityOfAllElements(logRows));
    }

    public UserTablePage open() {
        return (UserTablePage) super.open(USER_TABLE_PAGE_URL);
    }

    public List<WebElement> getRowNumbers() {
        return userTableComponent.numberCells;
    }

    public List<WebElement> getTypeDropdowns() {
        return userTableComponent.typeDropdowns;
    }

    public List<String> getUsernames() {
        return userTableComponent.usernameCells.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getDescriptions() {
        return userTableComponent.getDescriptions();
    }

    public List<WebElement> getCheckboxes() {
        return userTableComponent.checkboxes;
    }

    public List<WebElement> getUserTableRows() {
        return userTableComponent.rows;
    }

    public String getUserTableText() {
        return wait.until(ExpectedConditions.visibilityOf(checkboxSergeyIvan)).getText()
                   .replaceAll("\n", " ");
    }

    public List<String> getDropdownsValuesForUser(String user) {
        int row = getRowWithUsername(user);
        Select dropdown = new Select(userTableComponent.typeDropdowns.get(row));
        List<WebElement> dropdownValues = dropdown.getOptions();
        return dropdownValues.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void chooseCheckBoxForUser(String username) {
        int row = getRowWithUsername(username);
        userTableComponent.checkboxes.get(row).click();
    }

    public String getLogBodyText() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(logSection))
                   .stream().map(WebElement::getText).collect(Collectors.joining(""));
    }

    private int getRowWithUsername(String username) throws NullPointerException {
        int rowsNum = userTableComponent.rows.size();
        int row = rowsNum + 1;
        for (int i = 0; i < rowsNum; i++) {
            if (userTableComponent.getUsernameInRow(i).equals(username)) {
                row = i;
            }
        }
        if (row == rowsNum + 1) {
            throw new NullPointerException("No row with username: " + username + ".");
        }
        return row;
    }
}
