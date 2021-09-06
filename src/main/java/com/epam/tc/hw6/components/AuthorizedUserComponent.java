package com.epam.tc.hw6.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizedUserComponent extends AbstractComponent {

    @FindBy(id = "user-name")
    private WebElement username;

    AuthorizedUserComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(username));
    }

    public String getUsername() {
        return username.getText();
    }
}
