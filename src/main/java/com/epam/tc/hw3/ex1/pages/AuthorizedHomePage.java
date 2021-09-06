package com.epam.tc.hw3.ex1.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuthorizedHomePage extends HomePage {

    public AuthorizedHomePage(WebDriver driver, HomePage homePage) {
        super(driver);
        load();
        url = homePage.getUrl();
        title = homePage.getTitle();
        header = homePage.getHeader();
        leftSection = homePage.getLeftSection();
    }

    public String getUsername() {
        return header.getUsername();
    }

    public List<WebElement> getHeaderSectionItems() {
        return header.getSectionItems();
    }
}
