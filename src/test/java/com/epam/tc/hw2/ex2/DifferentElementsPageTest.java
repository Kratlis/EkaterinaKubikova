package com.epam.tc.hw2.ex2;

import com.epam.tc.hw2.HomePageTest;
import com.epam.tc.hw2.Utils;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class DifferentElementsPageTest extends HomePageTest {

    @Test
    public void testHomePage() {
        SoftAssertions softAssert = new SoftAssertions();

        // 1. Open test site by URL
        openHomePage();

        // 2. Assert Browser title
        homePageOpenTest(softAssert);

        // 3. Perform login
        login();

        // 4. Assert User name in the left-top side of screen that user is logged
        loginTest(softAssert);

        // 5. Open through the header menu Service -> Different Elements Page
        openDifferentElementsPage();
        pageDifferentElementsOpenTest(softAssert);

        // 6. Select checkboxes
        selectCheckboxes();
        selectCheckboxesTest(softAssert);

        // 7. Select radio
        selectRadio();
        selectRadioTest(softAssert);

        // 8. Select in dropdown
        selectDropdown();
        selectDropdownTest(softAssert);

        // 9. Assert that
        //      - for each checkbox there is an individual log row and value is corresponded
        //        to the status of checkbox
        //      - for radio button there is a log row and value is corresponded to the status
        //        of radio button
        //      - for dropdown there is a log row and value is corresponded to the selected value
        logTest(softAssert);
        softAssert.assertAll();
    }

    private void openDifferentElementsPage() {
        WebElement service = wait.until(ExpectedConditions.elementToBeClickable(
            By.className("dropdown")
        ));
        service.click();
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[text()='Different elements']")
        )).click();
    }

    private void pageDifferentElementsOpenTest(SoftAssertions softAssert) {
        softAssert.assertThat(driver.getCurrentUrl()).isEqualTo(Utils.DIFFERENT_ELEMENTS_PAGE_URL);
        softAssert.assertThat(driver.getTitle()).isEqualTo(Utils.DIFFERENT_ELEMENTS_PAGE_TITLE);
    }

    private void selectCheckboxes() {
        for (int i = 0; i < Utils.checkboxesNames.size(); i++) {
            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(., '" + Utils.checkboxesNames.get(i) + "')]")
            ));
            checkbox.click();
        }
    }

    private void selectCheckboxesTest(SoftAssertions softAssert) {
        List<WebElement> checkboxes = new ArrayList<>();
        for (int i = 0; i < Utils.checkboxesNames.size(); i++) {
            checkboxes.add(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(., '" + Utils.checkboxesNames.get(i) + "')]")
            )));
        }
        softAssert.assertThat(checkboxes)
                  .allMatch(checkbox -> checkbox.findElement(By.tagName("input")).isSelected());
    }

    private void selectRadio() {
        WebElement radio = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[contains(., '" + Utils.RADIO_NAME + "')]")
        ));
        radio.click();
    }

    private void selectRadioTest(SoftAssertions softAssert) {
        WebElement radio = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[contains(., '" + Utils.RADIO_NAME + "')]")
        ));
        softAssert.assertThat(radio.findElement(By.tagName("input")).isSelected()).isTrue();
    }

    private void selectDropdown() {
        Select colors = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".colors .uui-form-element")
        )));
        colors.selectByVisibleText(Utils.DROPDOWN_OPTION_NAME);
    }

    private void selectDropdownTest(SoftAssertions softAssert) {
        Select colors = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".colors .uui-form-element")
        )));
        String actualOption = colors.getFirstSelectedOption().getText();
        softAssert.assertThat(actualOption).isEqualTo(Utils.DROPDOWN_OPTION_NAME);
    }

    public void logTest(SoftAssertions softAssert) {
        WebElement logPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.className("logs")
        ));
        List<String> checkboxesLogRows = new ArrayList<>();
        for (int i = 0; i < Utils.checkboxesNames.size(); i++) {
            checkboxesLogRows.add(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(@class,'logs')]/li[contains(., '" + Utils.checkboxesNames.get(i) + "')]")
            )).getText());
        }
        softAssert.assertThat(checkboxesLogRows)
                  .allMatch(checkboxesLogRow -> {
                      for (int i = 0; i < Utils.checkboxesNames.size(); i++) {
                          if (
                              checkboxesLogRow.matches(Utils.checkboxesLogs.get(i))) {
                              return true;
                          }
                      }
                      return false;
                  });
        WebElement radioLogRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(@class,'logs')]/li[contains(., '" + Utils.RADIO_NAME + "')]")
        ));
        softAssert.assertThat(radioLogRow.getText().matches(Utils.RADIO_LOG)).isTrue();
        WebElement dropdownLogRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(@class,'logs')]/li[contains(., '" + Utils.DROPDOWN_OPTION_NAME + "')]")
        ));
        softAssert.assertThat(dropdownLogRow.getText().matches(Utils.DROPDOWN_OPTION_LOG)).isTrue();
    }
}
