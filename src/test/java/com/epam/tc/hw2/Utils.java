package com.epam.tc.hw2;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Utils {
    public static final String HOME_PAGE_TITLE = "Home Page";
    public static final String DIFFERENT_ELEMENTS_PAGE_TITLE = "Different Elements";
    public static final String HOME_PAGE_URL = "https://jdi-testing.github.io/jdi-light/index.html";
    public static final String DIFFERENT_ELEMENTS_PAGE_URL =
        "https://jdi-testing.github.io/jdi-light/different-elements.html";
    public static final String USERNAME = "ROMAN IOVLEV";
    public static final String LOGIN = "Roman";
    public static final String PASSWORD = "Jdi1234";
    public static final List<String> menuButtons =
        List.of("HOME", "CONTACT FORM", "SERVICE", "METALS & COLORS");
    public static final int IMAGES_NUMBER = 4;
    public static final List<String> imagesTexts = List.of(
        "To include good practices\nand ideas from successful\nEPAM project",
        "To be flexible and\ncustomizable",
        "To be multiplatform",
        "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…");
    public static final List<String> leftSectionItemsTexts = List.of(
        "Home",
        "Contact form",
        "Service",
        "Metals & Colors",
        "Elements packs"
    );
    public static final int FRAME_NUMBER = 4;
    public static final List<String> checkboxesNames = List.of("Water", "Wind");
    public static final List<String> checkboxesLogs = List.of(".*Water.*true", ".*Wind.*true");
    public static final String RADIO_NAME = "Selen";
    public static final String RADIO_LOG = ".*metal.*" + RADIO_NAME;
    public static final String DROPDOWN_OPTION_NAME = "Yellow";
    public static final String DROPDOWN_OPTION_LOG = ".*Colors.*" + DROPDOWN_OPTION_NAME;

    public static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }
}
