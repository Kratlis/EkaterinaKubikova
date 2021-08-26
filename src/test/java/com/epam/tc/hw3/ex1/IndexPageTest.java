package com.epam.tc.hw3.ex1;

import com.epam.tc.hw3.HomePageTest;
import com.epam.tc.hw3.Utils;
import com.epam.tc.hw3.ex1.components.LeftSectionComponent;
import com.epam.tc.hw3.ex1.pages.FrameButtonPage;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class IndexPageTest extends HomePageTest {

    @Test
    public void testIndexPage() {
        SoftAssertions softAssertions = new SoftAssertions();

        // 1. Open test site by URL
        openHomePage();

        // 2. Assert Browser title
        homePageOpenTest(softAssertions, unauthorizedHomePage);

        // 3. Perform login
        login();

        // 4. Assert User name in the left-top side of screen that user is logged
        loginTest(softAssertions);

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

    private void headerSectionItemsTest(SoftAssertions softAssertions) {
        List<WebElement> sectionItems = authorizedHomePage.getHeaderSectionItems();
        softAssertions.assertThat(sectionItems.size()).isEqualTo(Utils.menuButtons.size());

        List<String> sectionItemsTitles = sectionItems
            .stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertions.assertThat(sectionItemsTitles)
                     .containsExactlyElementsOf(Utils.menuButtons);
    }

    private void indexPageImagesTest(SoftAssertions softAssertions) {
        List<WebElement> images = authorizedHomePage.getImages();
        softAssertions.assertThat(images)
                     .hasSize(Utils.IMAGES_NUMBER)
                     .allMatch(WebElement::isDisplayed);
    }

    private void indexPageTextsTest(SoftAssertions softAssertions) {
        List<String> texts =
            authorizedHomePage.getTexts().stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertions.assertThat(texts)
                     .hasSize(Utils.imagesTexts.size())
                     .containsExactlyElementsOf(Utils.imagesTexts);
    }

    private void iframeTest(SoftAssertions softAssertions) {
        WebElement button = null;
        for (int i = 0; i < Utils.FRAME_NUMBER; i++) {
            WebElement iframe = authorizedHomePage.getIframes().get(i);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
            try {
                FrameButtonPage frameButtonPage = new FrameButtonPage(driver);
                button = frameButtonPage.getFrameButton();
                break;
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            } finally {
                driver.switchTo().defaultContent();
            }
        }
        softAssertions.assertThat(button).isNotNull();
    }

    private void leftSectionItemsTest(SoftAssertions softAssertions) {
        LeftSectionComponent leftSection = authorizedHomePage.getLeftSection();
        List<WebElement> leftSectionItems = leftSection.getLeftSectionItems();
        softAssertions.assertThat(leftSectionItems)
                     .hasSize(Utils.leftSectionItemsTexts.size())
                     .allMatch(WebElement::isDisplayed);

        List<String> leftSectionItemsTexts =
            leftSectionItems.stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertions.assertThat(leftSectionItemsTexts)
                     .containsExactlyElementsOf(Utils.leftSectionItemsTexts);
    }
}
