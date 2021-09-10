package com.epam.tc.hw3.ex1.components;

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

    public void login(String name, String password) {
        sendKeysToNameField(name);
        sendKeysToPasswordField(password);
        loginButton.click();
    }

    private void sendKeysToNameField(String name) {
        nameField.sendKeys(name);
    }

    private void sendKeysToPasswordField(String password) {
        passwordField.sendKeys(password);
    }
}
