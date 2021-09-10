package com.epam.tc.hw6.utils;

import com.epam.tc.hw6.exceptions.WebDriverTypeException;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import javax.management.openmbean.InvalidOpenTypeException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class WebDriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private static final String REMOTE_DRIVER_TYPE = "remote";
    private static final String LOCAL_DRIVER_TYPE = "local";

    private WebDriverFactory() {
    }

    public static WebDriver createWebDriver(final String driverType, final String browserName) {
        if (REMOTE_DRIVER_TYPE.equalsIgnoreCase(driverType)) {
            return createRemoteWebDriver(browserName);
        } else if (LOCAL_DRIVER_TYPE.equalsIgnoreCase(driverType)) {
            return createLocalDriver(browserName);
        } else {
            throw new WebDriverTypeException(String.format("Unsupported driver type: %s", driverType));
        }
    }

    /* Local Web Driver creation */
    private static WebDriver createLocalDriver(final String browserName) {
        WebDriver driver;
        switch (browserName.toLowerCase(Locale.ROOT)) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case FIREFOX:
                driver = createFirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException(String.format("Unsupported browser: %s", browserName));
        }

        return driver;
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    /* Remote Web Driver creation */
    private static WebDriver createRemoteWebDriver(final String browserName) {
        Capabilities capabilities;
        if (CHROME.equalsIgnoreCase(browserName)) {
            capabilities = createRemoteChromeCapabilities();
        } else if (FIREFOX.equalsIgnoreCase(browserName)) {
            capabilities = createRemoteFirefoxCapabilities();
        } else {
            throw new IllegalArgumentException(String.format("Unsupported browser: %s", browserName));
        }

        try {
            return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new InvalidOpenTypeException("Incorrect selenium grid url");
        }
    }

    private static Capabilities createRemoteFirefoxCapabilities() {
        return new FirefoxOptions();
    }

    private static Capabilities createRemoteChromeCapabilities() {
        return new ChromeOptions();
    }
}
