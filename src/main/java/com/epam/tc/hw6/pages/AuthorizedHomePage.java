package com.epam.tc.hw6.pages;

import org.openqa.selenium.WebDriver;

public class AuthorizedHomePage extends HomePage {

    public AuthorizedHomePage(WebDriver driver, HomePage homePage) {
        super(driver);
        load();
        url = homePage.url;
        title = homePage.title;
        header = homePage.header;
        leftSection = homePage.leftSection;
    }

    public String getUsername() {
        return header.getUsername();
    }
}
