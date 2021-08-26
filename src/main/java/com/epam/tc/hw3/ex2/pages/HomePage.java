package com.epam.tc.hw3.ex2.pages;

import com.epam.tc.hw3.ex2.components.HeaderComponent;
import com.epam.tc.hw3.ex2.components.ServiceMenuComponent;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    static final String HOME_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    protected HeaderComponent header;

    protected HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        return ((HomePage) open(HOME_URL)).load();
    }

    protected HomePage load() {
        header = new HeaderComponent(driver);
        return this;
    }

    public DifferentElementsPage openDifferentElementsPage() {
        return header.openServiceMenuItem().chooseDifferentElements();
    }

    public ServiceMenuComponent openServiceMenuComponent() {
        return header.openServiceMenuItem();

    }

    public HeaderComponent getHeader() {
        return header;
    }
}
