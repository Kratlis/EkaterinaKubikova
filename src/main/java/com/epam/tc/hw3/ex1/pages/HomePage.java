package com.epam.tc.hw3.ex1.pages;

import com.epam.tc.hw3.ex1.components.HeaderComponent;
import com.epam.tc.hw3.ex1.components.LeftSectionComponent;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage {

    static final String HOME_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    protected HeaderComponent header;
    protected LeftSectionComponent leftSection;

    @FindBy(className = "benefit-icon")
    List<WebElement> images;

    @FindBy(className = "benefit-txt")
    List<WebElement> texts;

    @FindBy(tagName = "iframe")
    List<WebElement> iframes;

    protected HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        open(HOME_URL);
        load();
    }

    protected void load() {
        header = new HeaderComponent(driver);
        leftSection = new LeftSectionComponent(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOfAllElements(images));
        wait.until(ExpectedConditions.visibilityOfAllElements(texts));
        wait.until(ExpectedConditions.visibilityOfAllElements(iframes));
    }

    public List<WebElement> getImages() {
        return images;
    }

    public List<WebElement> getTexts() {
        return texts;
    }

    public List<WebElement> getIframes() {
        return iframes;
    }

    public LeftSectionComponent getLeftSection() {
        return leftSection;
    }

    public HeaderComponent getHeader() {
        return header;
    }
}
