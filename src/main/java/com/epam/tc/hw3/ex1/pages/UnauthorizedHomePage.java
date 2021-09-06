package com.epam.tc.hw3.ex1.pages;

import org.openqa.selenium.WebDriver;

public class UnauthorizedHomePage extends HomePage {

    public UnauthorizedHomePage(WebDriver driver) {
        super(driver);
        open();
    }

    public void login(String name, String password) {
        header.login(name, password);
    }
}
