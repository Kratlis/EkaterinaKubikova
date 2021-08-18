package com.epam.tc.hw3.ex1.components;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderComponent extends AbstractComponent {

    @FindBy(css = ".nav > li")
    List<WebElement> sectionItems;

    AuthorizedUserComponent authorizedUserComponent;
    UnauthorizedUserComponent unauthorizedUserComponent;

    public HeaderComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(sectionItems));
        unauthorizedUserComponent = new UnauthorizedUserComponent(driver);
    }

    public void login(String name, String password) {
        unauthorizedUserComponent.login(name, password);
        unauthorizedUserComponent = null;
        authorizedUserComponent = new AuthorizedUserComponent(driver);
    }

    public List<WebElement> getSectionItems() {
        return sectionItems;
    }

    public String getUsername() {
        return authorizedUserComponent.getUsername();
    }

}
