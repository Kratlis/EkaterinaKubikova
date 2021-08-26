package com.epam.tc.hw3.pages;

import com.epam.tc.hw3.components.CheckboxRowComponent;
import com.epam.tc.hw3.components.DropdownListComponent;
import com.epam.tc.hw3.components.HeaderComponent;
import com.epam.tc.hw3.components.LogComponent;
import com.epam.tc.hw3.components.RadioRowComponent;
import org.openqa.selenium.WebDriver;

public class DifferentElementsPage extends AbstractPage {

    static final String DIFFERENT_ELEMENTS_PAGE_URL = "https://jdi-testing.github.io/jdi-light/different-elements.html";
    protected HeaderComponent header;

    public CheckboxRowComponent checkboxRow;
    public RadioRowComponent radioRow;
    public DropdownListComponent dropdownList;

    public DifferentElementsPage(WebDriver driver) {
        super(driver);
        open(DIFFERENT_ELEMENTS_PAGE_URL);
        load();
    }

    public LogComponent initLogComponent() {
        return new LogComponent(driver);
    }

    private DifferentElementsPage load() {
        header = new HeaderComponent(driver);
        checkboxRow = new CheckboxRowComponent(driver);
        radioRow = new RadioRowComponent(driver);
        dropdownList = new DropdownListComponent(driver);
        return this;
    }
}
