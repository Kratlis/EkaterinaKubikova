package com.epam.tc.hw7;

import static com.epam.tc.hw7.SiteJdi.header;
import static com.epam.tc.hw7.SiteJdi.homePage;
import static com.epam.tc.hw7.SiteJdi.metalsColorsPage;
import static com.epam.tc.hw7.entities.LeftMenuData.MetalsColors;
import static com.epam.tc.hw7.states.States.shouldBeLoggedIn;
import static com.epam.tc.hw7.states.States.shouldBeLoggedOut;

import com.epam.tc.hw7.entities.MetalsColorsFormData;
import com.epam.tc.hw7.testng.TestNGListener;
import com.epam.tc.hw7.utils.MetalsColorsDataProvider;
import com.epam.tc.hw7.utils.Utils;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListener.class)
public class MetalsColorsTest implements TestsInit {

    @BeforeMethod
    public void openMetalsColorsPage() {
        shouldBeLoggedOut();
        homePage.shouldBeOpened();
        shouldBeLoggedIn();

        header.select(MetalsColors);
        metalsColorsPage.checkOpened();
    }

    @Test(dataProvider = "form fields", dataProviderClass = MetalsColorsDataProvider.class)
    @Story("User can submit Metals & Colors form")
    public void submitMetalsColorsFormTest(MetalsColorsFormData formData) {
        metalsColorsPage.form.fill(formData);
        metalsColorsPage.form.check(formData);
        metalsColorsPage.form.submit();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(metalsColorsPage.resultPanel.toString())
                      .as("Check that result in Result Panel contains correct form fields' values.")
                      .isEqualTo(Utils.getExpectedResult(formData));
        softAssertions.assertAll();
    }
}
