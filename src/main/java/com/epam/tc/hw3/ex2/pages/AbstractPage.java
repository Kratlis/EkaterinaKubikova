package com.epam.tc.hw3.ex2.pages;

import com.epam.tc.hw3.ex2.components.AbstractComponent;
import org.openqa.selenium.WebDriver;

public class AbstractPage extends AbstractComponent {
    protected String title = null;
    protected String url = null;

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

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
