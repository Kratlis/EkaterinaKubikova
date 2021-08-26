package com.epam.tc.hw3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FrameButtonPage extends AbstractPage {
    @FindBy(css = "[value='Frame Button']")
    public WebElement frameButton;

    public FrameButtonPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
