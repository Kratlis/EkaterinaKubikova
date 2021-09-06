package com.epam.tc.hw4.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginComponent extends AbstractComponent {

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    protected LoginComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(nameField));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    public LoginComponent login(String name, String password) {
        return sendKeysToNameField(name).sendKeysToPasswordField(password).submit();
    }

    private LoginComponent sendKeysToNameField(String name) {
        nameField.sendKeys(name);
        return this;
    }

    private LoginComponent sendKeysToPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    private LoginComponent submit() {
        loginButton.click();
        return this;
    }
}
