package com.epam.tc.hw3.ex1;

import com.epam.tc.hw3.ex1.components.LeftSectionComponent;
import com.epam.tc.hw3.ex1.pages.AbstractPage;
import com.epam.tc.hw3.ex1.pages.AuthorizedHomePage;
import com.epam.tc.hw3.ex1.pages.FrameButtonPage;
import com.epam.tc.hw3.ex1.pages.UnauthorizedHomePage;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest {

    private WebDriverWait wait;
    private WebDriver driver;
    private UnauthorizedHomePage unauthorizedHomePage;
    private AuthorizedHomePage authorizedHomePage;

    @BeforeMethod
    public void prepareDriver() {
        driver = Utils.getChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    // 12. Close Browser
    @AfterMethod
    private void close() {
        driver.quit();
    }

    @Test
    public void testHomePage() {
        SoftAssertions softAssertion = new SoftAssertions();
        // 1. Open test site by URL
        openHomePage();

        // 2. Assert Browser title
        pageOpenTest(unauthorizedHomePage, softAssertion);

        // 3. Perform login
        login();

        // 4. Assert Username is logged
        loginTest(softAssertion);

        // 5. Assert that there are 4 items on the header section are displayed,
        //    and they have proper texts
        headerSectionItemsTest(softAssertion);

        // 6. Assert that there are 4 images on the Index Page,
        //    and they are displayed
        indexPageImagesTest(softAssertion);

        // 7. Assert that there are 4 texts on the Index Page under icons,
        //    and they have proper text
        indexPageTextsTest(softAssertion);

        // 8. Assert that there is the iframe with “Frame Button” exist
        // 9. Switch to the iframe and check that there is “Frame Button” in the iframe
        // 10. Switch to original window back
        iframeTest(softAssertion);

        // 11. Assert that there are 5 items in the Left Section are displayed,
        //     and they have proper text
        leftSectionItemsTest(softAssertion);
        softAssertion.assertAll();
    }

    private void openHomePage() {
        unauthorizedHomePage = new UnauthorizedHomePage(driver);
    }

    private void pageOpenTest(AbstractPage page, SoftAssertions softAssertion) {
        softAssertion.assertThat(page.getUrl())
                     .isEqualTo(Utils.HOME_PAGE_URL);
        softAssertion.assertThat(page.getTitle())
                     .isEqualTo(Utils.HOME_PAGE_TITLE);
    }

    private void login() {
        PropertyFileReader reader = new PropertyFileReader(Utils.PROPERTY_FILE_PATH);
        String name = reader.readName();
        String password = reader.readPassword();
        unauthorizedHomePage.login(name, password);
        authorizedHomePage = new AuthorizedHomePage(driver, unauthorizedHomePage);
    }

    private void loginTest(SoftAssertions softAssertion) {
        String expectedUsername = new PropertyFileReader(Utils.PROPERTY_FILE_PATH).readUsername();
        String actualUsername = authorizedHomePage.getUsername();

        softAssertion.assertThat(actualUsername).isEqualTo(expectedUsername);
    }

    private void headerSectionItemsTest(SoftAssertions softAssertion) {
        List<WebElement> sectionItems = authorizedHomePage.getHeaderSectionItems();
        softAssertion.assertThat(sectionItems.size()).isEqualTo(Utils.menuButtons.size());

        List<String> sectionItemsTitles = sectionItems
            .stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertion.assertThat(sectionItemsTitles)
                     .containsExactlyElementsOf(Utils.menuButtons);
    }

    private void indexPageImagesTest(SoftAssertions softAssertion) {
        List<WebElement> images = authorizedHomePage.getImages();
        softAssertion.assertThat(images)
                     .hasSize(Utils.IMAGES_NUMBER)
                     .allMatch(WebElement::isDisplayed);
    }

    private void indexPageTextsTest(SoftAssertions softAssertion) {
        List<String> texts =
            authorizedHomePage.getTexts().stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertion.assertThat(texts)
                     .hasSize(Utils.imagesTexts.size())
                     .containsExactlyElementsOf(Utils.imagesTexts);
    }

    private void iframeTest(SoftAssertions softAssertion) {
        WebElement button = null;
        for (int i = 0; i < Utils.FRAME_NUMBER; i++) {
            WebElement iframe = authorizedHomePage.getIframes().get(i);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
            try {
                FrameButtonPage frameButtonPage = new FrameButtonPage(driver);
                button = frameButtonPage.getFrameButton();
            } catch (NoSuchElementException e) {
                continue;
            } finally {
                driver.switchTo().defaultContent();
            }
            break;
        }
        softAssertion.assertThat(button).isNotNull();
    }

    private void leftSectionItemsTest(SoftAssertions softAssertion) {
        LeftSectionComponent leftSection = authorizedHomePage.getLeftSection();
        List<WebElement> leftSectionItems = leftSection.getLeftSectionItems();
        softAssertion.assertThat(leftSectionItems)
                     .hasSize(Utils.leftSectionItemsTexts.size())
                     .allMatch(WebElement::isDisplayed);

        List<String> leftSectionItemsTexts =
            leftSectionItems.stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertion.assertThat(leftSectionItemsTexts)
                     .containsExactlyElementsOf(Utils.leftSectionItemsTexts);
    }
}
