package com.epam.tc.hw2.ex1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utils {
    public static final String CHROME_SYSTEM_PROPERTY_NAME = "webdriver.chrome.driver";
    public static final String CHROME_SYSTEM_PROPERTY_PATH = "/Users/katya/browser_drivers/chromedriver.exe";
    public static final String HOME_PAGE_TITLE = "Home Page";
    public static final String HOME_PAGE_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    public static final String USERNAME = "ROMAN IOVLEV";
    public static final String LOGIN = "Roman";
    public static final String PASSWORD = "Jdi1234";
    public static final int MENU_BUTTONS_NUMBER = 4;
    public static final List<String> menuButtons = new ArrayList<>(
        Arrays.asList("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS"));
    public static final int IMAGES_NUMBER = 4;
    public static final List<By> imagesXpathSelectors = new ArrayList<>(
        Arrays.asList(
            By.xpath("//span[contains(@class, 'icon-practise')]/../following-sibling::*[@class='benefit-txt']"),
            By.xpath("//span[contains(@class, 'icon-custom')]/../following-sibling::*[@class='benefit-txt']"),
            By.xpath("//span[contains(@class, 'icon-multi')]/../following-sibling::*[@class='benefit-txt']"),
            By.xpath("//span[contains(@class, 'icon-base')]/../following-sibling::*[@class='benefit-txt']"))
    );
    public static final int TEXTS_NUMBER = 4;
    public static final List<String> imagesDescriptions = new ArrayList<>(
        Arrays.asList(
            "To include good practices\nand ideas from successful\nEPAM project",
            "To be flexible and\ncustomizable",
            "To be multiplatform",
            "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…")
    );
    public static final int LEFT_SECTION_ITEMS_NUM = 5;
    public static final List<String> leftSectionItemsTexts = new ArrayList<>(
        Arrays.asList(
            "Home",
            "Contact form",
            "Service",
            "Metals & Colors",
            "Elements packs"
        )
    );
    public static final int FRAME_NUMBER = 4;

    public static WebDriver getChromeDriver() {
        System.setProperty(CHROME_SYSTEM_PROPERTY_NAME, CHROME_SYSTEM_PROPERTY_PATH);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }
}
