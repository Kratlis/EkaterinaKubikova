package com.epam.tc.hw6.utils;

import java.util.Objects;
import org.openqa.selenium.WebDriver;

public final class WebDriverSingleton {

    private static WebDriver driver;

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        String driverType = System.getProperty("driver.type", "local");
        String browserName = System.getProperty("browser.name", "chrome");
        if (Objects.isNull(driver)) {
            driver = WebDriverFactory.createWebDriver(driverType, browserName);
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}   
