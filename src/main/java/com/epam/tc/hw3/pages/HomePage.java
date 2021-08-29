package com.epam.tc.hw3.pages;

import com.epam.tc.hw3.components.HeaderComponent;
import com.epam.tc.hw3.components.LeftSectionComponent;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage {
    static final String HOME_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    public HeaderComponent header;
    public LeftSectionComponent leftSection;

    @FindBy(className = "benefit-icon")
    public List<WebElement> images;

    @FindBy(className = "benefit-txt")
    public List<WebElement> texts;

    @FindBy(tagName = "iframe")
    public List<WebElement> iframes;

    protected HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        return ((HomePage) open(HOME_URL)).load();
    }

    protected HomePage load() {
        header = new HeaderComponent(driver);
        leftSection = new LeftSectionComponent(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(images));
        wait.until(ExpectedConditions.visibilityOfAllElements(texts));
        wait.until(ExpectedConditions.visibilityOfAllElements(iframes));
        return this;
    }

    public DifferentElementsPage openDifferentElementsPage() {
        return header.openServiceMenuItem().chooseDifferentElements();
    }
}
