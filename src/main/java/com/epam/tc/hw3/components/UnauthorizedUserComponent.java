package com.epam.tc.hw3.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UnauthorizedUserComponent extends AbstractComponent {

    @FindBy(css = "[href='#']")
    private WebElement loginButton;

    UnauthorizedUserComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    UnauthorizedUserComponent login(String name, String password) {
        loginButton.click();
        new LoginComponent(driver).login(name, password);
        return this;
    }
}
