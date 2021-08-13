package com.epam.tc.hw2.ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utils {
    public static final String CHROME_SYSTEM_PROPERTY_NAME = "webdriver.chrome.driver";
    public static final String CHROME_SYSTEM_PROPERTY_PATH = "/Users/katya/browser_drivers/chromedriver.exe";
    public static final String HOME_PAGE_TITLE = "Home Page";
    public static final String HOME_PAGE_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    public static final String DIFFERENT_ELEMENTS_PAGE_URL =
        "https://jdi-testing.github.io/jdi-light/different-elements.html";
    public static final String DIFFERENT_ELEMENTS_PAGE_TITLE = "Different Elements";

    public static final String USERNAME = "ROMAN IOVLEV";
    public static final List<String> checkboxesNames = new ArrayList<>(
        Arrays.asList("Water", "Wind")
    );
    public static final List<String> checkboxesLogs = new ArrayList<>(
        Arrays.asList(".*Water.*true", ".*Wind.*true")
    );
    public static final String RADIO_NAME = "Selen";
    public static final String RADIO_LOG = ".*metal.*" + RADIO_NAME;
    public static final String DROPDOWN_OPTION_NAME = "Yellow";
    public static final String DROPDOWN_OPTION_LOG = ".*Colors.*" + DROPDOWN_OPTION_NAME;

    public static WebDriver getChromeDriver() {
        System.setProperty(CHROME_SYSTEM_PROPERTY_NAME, CHROME_SYSTEM_PROPERTY_PATH);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }
}
