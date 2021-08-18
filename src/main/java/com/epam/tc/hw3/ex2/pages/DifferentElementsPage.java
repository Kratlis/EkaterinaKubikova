package com.epam.tc.hw3.ex2.pages;

import com.epam.tc.hw3.ex2.components.CheckboxRowComponent;
import com.epam.tc.hw3.ex2.components.DropdownListComponent;
import com.epam.tc.hw3.ex2.components.HeaderComponent;
import com.epam.tc.hw3.ex2.components.LogComponent;
import com.epam.tc.hw3.ex2.components.RadioRowComponent;
import org.openqa.selenium.WebDriver;

public class DifferentElementsPage extends AbstractPage {

    static final String DIFFERENT_ELEMENTS_PAGE_URL = "https://jdi-testing.github.io/jdi-light/different-elements.html";
    protected HeaderComponent header;

    CheckboxRowComponent checkboxRow;
    RadioRowComponent radioRow;
    DropdownListComponent dropdownList;
    LogComponent log;

    public DifferentElementsPage(WebDriver driver) {
        super(driver);
        open();
    }

    public DifferentElementsPage open() {
        return (DifferentElementsPage) open(DIFFERENT_ELEMENTS_PAGE_URL);
    }

    public DifferentElementsPage load() {
        header = new HeaderComponent(driver);
        checkboxRow = new CheckboxRowComponent(driver);
        radioRow = new RadioRowComponent(driver);
        dropdownList = new DropdownListComponent(driver);
        return this;
    }

    public CheckboxRowComponent getCheckboxRow() {
        return checkboxRow;
    }

    public RadioRowComponent getRadioRow() {
        return radioRow;
    }

    public DropdownListComponent getDropdownList() {
        return dropdownList;
    }

    public LogComponent getLog() {
        log = new LogComponent(driver);
        return log;
    }
}
