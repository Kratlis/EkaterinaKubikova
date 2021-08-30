package com.epam.tc.hw5.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Object driver = result.getTestContext().getAttribute("driver");

        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Utils.makeScreenshotAttachment("Screenshot on failure", screenshot);
        }
    }
}
