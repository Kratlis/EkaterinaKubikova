package com.epam.tc.hw3.ex1.pages;

import com.epam.tc.hw3.ex1.components.AbstractComponent;
import org.openqa.selenium.WebDriver;

public class AbstractPage extends AbstractComponent {
    protected String title = null;
    protected String url = null;

    protected AbstractPage(WebDriver driver) {
        super(driver);
    }

    protected void open(String url) {
        this.url = url;
        driver.navigate().to(this.url);
        this.url = driver.getCurrentUrl();
        this.title = driver.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
