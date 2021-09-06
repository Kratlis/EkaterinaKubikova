package com.epam.tc.hw6.ex1;

import com.epam.tc.hw6.HomePageTest;
import com.epam.tc.hw6.components.LeftSectionComponent;
import com.epam.tc.hw6.pages.FrameButtonPage;
import com.epam.tc.hw6.utils.PropertyFileReader;
import com.epam.tc.hw6.utils.Utils;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class IndexPageTest extends HomePageTest {

    @Test(description = "Test Home Page elements")
    @Description("Test login, header menu, images on the page, images' descriptions on the page, "
        + "iframes on the page. Find iframe with FrameButton. Test left menu.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Home Page")
    @Story(
        "User can login and see header section elements, left section elements, "
            + "images, images' descriptions and iframe with Frame Button.")
    public void testIndexPage() {
        SoftAssertions softAssertions = new SoftAssertions();

        // 1. Open test site by URL
        openHomePage();

        // 2. Assert Browser title
        homePageOpenTest(softAssertions, unauthorizedHomePage);

        // 3. Perform login
        PropertyFileReader.init(Utils.PROPERTY_FILE_PATH);
        String name = PropertyFileReader.readName();
        String password = PropertyFileReader.readPassword();
        login(name, password);

        // 4. Assert Username in the left-top side of screen that user is logged
        String expectedUsername = PropertyFileReader.readUsername();
        loginTest(softAssertions, expectedUsername);

        // 5. Assert that there are 4 items on the header section are displayed,
        //    and they have proper texts
        headerSectionItemsTest(softAssertions);

        // 6. Assert that there are 4 images on the Index Page,
        //    and they are displayed
        indexPageImagesTest(softAssertions);

        // 7. Assert that there are 4 texts on the Index Page under icons,
        //    and they have proper text
        indexPageTextsTest(softAssertions);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        // 10. Switch to original window back
        iframeTest(softAssertions);

        // 11. Assert that there are 5 items in the Left Section are displayed,
        //     and they have proper text
        leftSectionItemsTest(softAssertions);
        softAssertions.assertAll();
    }

    @Step("Test number of header sections and their names")
    private void headerSectionItemsTest(SoftAssertions softAssertions) {
        Allure.addAttachment("Number of header sections", String.valueOf(Utils.menuButtons.size()));
        Allure.addAttachment("Header sections' names", String.valueOf(Utils.menuButtons));

        List<WebElement> sectionItems = authorizedHomePage.header.sectionItems;
        softAssertions.assertThat(sectionItems.size()).isEqualTo(Utils.menuButtons.size());

        List<String> sectionItemsTitles = sectionItems
            .stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertions.assertThat(sectionItemsTitles)
                      .containsExactlyElementsOf(Utils.menuButtons);
    }

    @Step("Test number of images and their visibility")
    private void indexPageImagesTest(SoftAssertions softAssertions) {
        Allure.addAttachment("Number of images", String.valueOf(Utils.IMAGES_NUMBER));

        List<WebElement> images = authorizedHomePage.images;
        softAssertions.assertThat(images)
                      .hasSize(Utils.IMAGES_NUMBER)
                      .allMatch(WebElement::isDisplayed);
    }

    @Step("Test images' descriptions")
    private void indexPageTextsTest(SoftAssertions softAssertions) {
        Allure.addAttachment("Number of images' descriptions", String.valueOf(Utils.imagesTexts.size()));
        Allure.addAttachment("Images' descriptions", String.valueOf(Utils.imagesTexts));

        List<String> texts =
            authorizedHomePage.texts.stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertions.assertThat(texts)
                      .hasSize(Utils.imagesTexts.size())
                      .containsExactlyElementsOf(Utils.imagesTexts);
    }

    @Step("Test existence of the iframe with Frame Button")
    private void iframeTest(SoftAssertions softAssertions) {
        WebElement button = null;
        for (int i = 0; i < authorizedHomePage.iframes.size(); i++) {
            WebElement iframe = authorizedHomePage.iframes.get(i);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
            try {
                FrameButtonPage frameButtonPage = new FrameButtonPage(driver);
                button = frameButtonPage.frameButton;
                break;
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            } finally {
                driver.switchTo().defaultContent();
            }
        }
        softAssertions.assertThat(button).isNotNull();
    }

    @Step("Test number of Left Section items and their names ")
    private void leftSectionItemsTest(SoftAssertions softAssertions) {
        Allure.addAttachment("Number of Left Section items",
            String.valueOf(Utils.leftSectionItemsTexts.size()));
        Allure.addAttachment("Left Section items' names",
            String.valueOf(Utils.leftSectionItemsTexts));

        LeftSectionComponent leftSection = authorizedHomePage.leftSection;
        List<WebElement> leftSectionItems = leftSection.leftSectionItems;
        softAssertions.assertThat(leftSectionItems)
                      .hasSize(Utils.leftSectionItemsTexts.size())
                      .allMatch(WebElement::isDisplayed);

        List<String> leftSectionItemsTexts =
            leftSectionItems.stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertions.assertThat(leftSectionItemsTexts)
                      .containsExactlyElementsOf(Utils.leftSectionItemsTexts);
    }
}
