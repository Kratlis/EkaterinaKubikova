package com.epam.tc.hw4;

import static io.qameta.allure.Allure.step;

import io.qameta.allure.Description;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StepsDemo {
    public static final String CAT_URL =
        "https://upload.wikimedia.org/wikipedia/commons/b/b6/Felis_catus-cat_on_snow.jpg";

    @BeforeMethod(description = "Setting up")
    public void setUp(ITestContext context) {
        step("read name");
        WebDriver driver = null;
        context.setAttribute("driver", driver);
    }

    @AfterMethod
    public void tearDown() {

    }

    @Test
    public void testWithoutName() {

    }

    @Test(description = "test with description")
    @Description("@Description description")
    public void testDescription() {
        step("Step 1");
    }

    @Test
    public void testFail() {
        Assertions.fail("manually failed");
    }

    @Test
    public void testAttachment() {
        attachCat();
        List<String> input = List.of("a", "b", "c");

        AttachmentDemo.makeStringAttachment(input);
        Assertions.assertThat(true).isTrue();
    }

    private void attachCat() {
        try (InputStream inputStream = new URL(CAT_URL).openStream()) {
            AttachmentDemo.attachFromInput("CATS", inputStream);
        } catch (IOException ignored) {
            return;
        }
    }
}
