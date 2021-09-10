package com.epam.tc.hw3.ex1.components;

import com.epam.tc.hw3.ex2.components.ServiceMenuComponent;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderComponent extends AbstractComponent {

    AuthorizedUserComponent authorizedUserComponent;
    UnauthorizedUserComponent unauthorizedUserComponent;

    @FindBy(css = ".nav li.dropdown")
    WebElement serviceMenuItem;

    @FindBy(css = ".nav > li")
    List<WebElement> sectionItems;

    public HeaderComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(sectionItems));
        wait.until(ExpectedConditions.elementToBeClickable(serviceMenuItem));
        unauthorizedUserComponent = new UnauthorizedUserComponent(driver);
    }

    public HeaderComponent login(String name, String password) {
        unauthorizedUserComponent.login(name, password);
        authorizedUserComponent = new AuthorizedUserComponent(driver);
        return this;
    }

    public ServiceMenuComponent openServiceMenuItem() {
        serviceMenuItem.click();
        return new ServiceMenuComponent(driver);
    }

    public List<WebElement> getSectionItems() {
        return sectionItems;
    }

    public String getUsername() {
        return authorizedUserComponent.getUsername();
    }
}
