package com.epam.tc.hw3.ex2.pages;

import org.openqa.selenium.WebDriver;

public class UnauthorizedHomePage extends HomePage {
    public UnauthorizedHomePage(WebDriver driver) {
        super(driver);
        open();
    }

    public AuthorizedHomePage login(String name, String password) {
        header.login(name, password);
        return new AuthorizedHomePage(driver, this);
    }
}
