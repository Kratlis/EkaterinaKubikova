package com.epam.tc.hw4.ex2;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utils {
    public static final String HOME_PAGE_TITLE = "Home Page";
    public static final String HOME_PAGE_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    public static final String PROPERTY_FILE_PATH = "src/test/resources/test.properties";
    public static final String DIFFERENT_ELEMENTS_PAGE_URL =
        "https://jdi-testing.github.io/jdi-light/different-elements.html";
    public static final String DIFFERENT_ELEMENTS_PAGE_TITLE = "Different Elements";
    public static final List<String> checkboxesNames = List.of("Water", "Wind");
    public static final String CHECKBOX_LOG_VALUE = "true";
    public static final String RADIO_NAME = "Selen";
    public static final String RADIO_LOG_VALUE = "metal";
    public static final String DROPDOWN_OPTION_NAME = "Yellow";
    public static final String DROPDOWN_LOG_VALUE = "Colors";

    public static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }
}
