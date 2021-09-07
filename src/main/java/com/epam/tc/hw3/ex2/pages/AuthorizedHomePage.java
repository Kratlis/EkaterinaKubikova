package com.epam.tc.hw3.ex2.pages;

import org.openqa.selenium.WebDriver;

public class AuthorizedHomePage extends HomePage {

    public AuthorizedHomePage(WebDriver driver, HomePage homePage) {
        super(driver);
        load();
        url = homePage.getUrl();
        title = homePage.getTitle();
        header = homePage.getHeader();
    }

    public String getUsername() {
        return header.getUsername();
    }
}
