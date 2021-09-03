package com.epam.tc.hw7;

import static com.epam.tc.SiteJdi.header;
import static com.epam.tc.SiteJdi.homePage;
import static com.epam.tc.SiteJdi.metalsColorsPage;
import static com.epam.tc.entities.LeftMenuData.MetalsColors;
import static com.epam.tc.hw7.states.States.shouldBeLoggedIn;
import static com.epam.tc.hw7.states.States.shouldBeLoggedOut;

import com.epam.tc.components.LogItem;
import com.epam.tc.entities.MetalsColorsFormData;
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
        softAssertions.assertThat(
                          metalsColorsPage.logs.stream().filter(LogItem::containsSubmitButtonClickedLog).count())
                      .isGreaterThanOrEqualTo(1);

        softAssertions.assertThat(metalsColorsPage.resultPanel.summary.text())
                      .isEqualTo(Utils.getExpectedSummaryResult(formData));

        softAssertions.assertThat(metalsColorsPage.resultPanel.elements.text())
                      .isEqualTo(Utils.getExpectedElementsResult(formData));

        softAssertions.assertThat(metalsColorsPage.resultPanel.color.text())
                      .isEqualTo(Utils.getExpectedColorResult(formData));

        softAssertions.assertThat(metalsColorsPage.resultPanel.metal.text())
                      .isEqualTo(Utils.getExpectedMetalResult(formData));

        softAssertions.assertThat(metalsColorsPage.resultPanel.vegetables.text())
                      .isEqualTo(Utils.getExpectedVegetablesResult(formData));
        softAssertions.assertAll();
    }
}
