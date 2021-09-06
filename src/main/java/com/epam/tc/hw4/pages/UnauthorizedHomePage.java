package com.epam.tc.hw4.pages;

import org.openqa.selenium.WebDriver;

public class UnauthorizedHomePage extends HomePage {
    public UnauthorizedHomePage(WebDriver driver) {
        super(driver);
    }

    public AuthorizedHomePage login(String name, String password) {
        header.login(name, password);
        return new AuthorizedHomePage(driver, this);
    }
}
