package com.epam.tc.hw4.pages;

import com.epam.tc.hw4.components.AbstractComponent;
import org.openqa.selenium.WebDriver;

public class AbstractPage extends AbstractComponent {
    public String title = null;
    public String url = null;

    protected AbstractPage(WebDriver driver) {
        super(driver);
    }

    protected AbstractPage open(String url) {
        this.url = url;
        driver.navigate().to(this.url);
        this.url = driver.getCurrentUrl();
        this.title = driver.getTitle();
        return this;
    }
}
