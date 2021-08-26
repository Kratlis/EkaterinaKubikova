package com.epam.tc.hw4.ex1;

import com.epam.tc.hw3.ex1.components.LeftSectionComponent;
import com.epam.tc.hw3.ex1.pages.AbstractPage;
import com.epam.tc.hw3.ex1.pages.AuthorizedHomePage;
import com.epam.tc.hw3.ex1.pages.FrameButtonPage;
import com.epam.tc.hw3.ex1.pages.UnauthorizedHomePage;
import com.epam.tc.hw4.utils.PropertyFileReader;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
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

    @BeforeMethod(description = "Prepare Chrome Web Driver")
    public void prepareDriver() {
        driver = Utils.getChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    // 12. Close Browser
    @AfterMethod(description = "Close browser")
    private void close() {
        driver.quit();
    }

    @Test(description = "Test Home Page elements")
    @Description("Test login, header menu, images on the page, images' descriptions on the page, "
        + "iframes on the page. Find iframe with FrameButton. Test left menu.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Home Page")
    @Story(
        "User can login and see header section elements, left section elements, "
            + "images, images' descriptions and iframe with Frame Button.")
    public void testHomePage() {
        SoftAssertions softAssertion = new SoftAssertions();

        // 1. Open test site by URL
        openHomePage();

        // 2. Assert Browser title
        pageOpenTest(softAssertion, unauthorizedHomePage);

        // 3. Perform login
        PropertyFileReader reader = new PropertyFileReader(Utils.PROPERTY_FILE_PATH);
        String name = reader.readName();
        String password = reader.readPassword();
        login(name, password);

        // 4. Assert Username is logged
        String expectedUsername = reader.readUsername();
        loginTest(softAssertion, expectedUsername);

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

    @Step("Open Home Page")
    private void openHomePage() {
        unauthorizedHomePage = new UnauthorizedHomePage(driver);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test opened page {page.title}")
    private void pageOpenTest(SoftAssertions softAssertion, AbstractPage page) {
        softAssertion.assertThat(page.getUrl())
                     .isEqualTo(Utils.HOME_PAGE_URL);
        softAssertion.assertThat(page.getTitle())
                     .isEqualTo(Utils.HOME_PAGE_TITLE);
    }

    @Step("Login as {name} : {password}")
    private void login(String name, String password) {
        unauthorizedHomePage.login(name, password);
        authorizedHomePage = new AuthorizedHomePage(driver, unauthorizedHomePage);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Step("Test user logged as {username}")
    private void loginTest(SoftAssertions softAssertion, String username) {
        String actualUsername = authorizedHomePage.getUsername();
        softAssertion.assertThat(actualUsername).isEqualTo(username);
    }

    @Step("Test number of header sections and their names")
    private void headerSectionItemsTest(SoftAssertions softAssertion) {
        Allure.addAttachment("Number of header sections", String.valueOf(Utils.menuButtons.size()));
        Allure.addAttachment("Header sections' names", String.valueOf(Utils.menuButtons));

        List<WebElement> sectionItems = authorizedHomePage.getHeaderSectionItems();
        softAssertion.assertThat(sectionItems)
                     .hasSize(Utils.menuButtons.size());

        List<String> sectionItemsTitles = sectionItems
            .stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertion.assertThat(sectionItemsTitles)
                     .containsExactlyElementsOf(Utils.menuButtons);
    }

    @Step("Test number of images and their visibility")
    private void indexPageImagesTest(SoftAssertions softAssertion) {
        Allure.addAttachment("Number of images", String.valueOf(Utils.IMAGES_NUMBER));
        List<WebElement> images = authorizedHomePage.getImages();
        softAssertion.assertThat(images)
                     .hasSize(Utils.IMAGES_NUMBER)
                     .allMatch(WebElement::isDisplayed);
    }

    @Step("Test images' descriptions")
    private void indexPageTextsTest(SoftAssertions softAssertion) {
        Allure.addAttachment("Number of images' descriptions", String.valueOf(Utils.imagesTexts.size()));
        Allure.addAttachment("Images' descriptions", String.valueOf(Utils.imagesTexts));

        List<String> texts =
            authorizedHomePage.getTexts().stream().map(WebElement::getText).collect(Collectors.toList());
        softAssertion.assertThat(texts)
                     .hasSize(Utils.imagesTexts.size())
                     .containsExactlyElementsOf(Utils.imagesTexts);
    }

    @Step("Test existence of the iframe with Frame Button")
    private void iframeTest(SoftAssertions softAssertion) {
        WebElement button = null;
        for (int i = 0; i < authorizedHomePage.getIframes().size(); i++) {
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

    @Step("Test number of Left Section items and their names ")
    private void leftSectionItemsTest(SoftAssertions softAssertion) {
        Allure.addAttachment("Number of Left Section items",
            String.valueOf(Utils.leftSectionItemsTexts.size()));
        Allure.addAttachment("Left Section items' names", String.valueOf(Utils.leftSectionItemsTexts));

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
