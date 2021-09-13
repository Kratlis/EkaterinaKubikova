package com.epam.tc.hw7.states;

import static com.epam.tc.hw7.SiteJdi.homePage;
import static com.epam.tc.hw7.SiteJdi.loginForm;
import static com.epam.tc.hw7.SiteJdi.logout;
import static com.epam.tc.hw7.SiteJdi.userIcon;
import static com.epam.tc.hw7.SiteJdi.userName;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.tc.hw7.entities.User;
import io.qameta.allure.Step;

/**
 * Created by Roman_Iovlev on 3/1/2018.
 */
public class States {
    private static void onSite() {
        if (!WebPage.getUrl().contains("https://jdi-testing.github.io/jdi-light/")) {
            homePage.open();
        }
    }

    @Step
    public static void shouldBeLoggedIn() {
        onSite();
        if (!userName.isDisplayed()) {
            login();
        }
    }

    @Step
    public static void login() {
        userIcon.click();
        loginForm.submit(new User(), "enter");
    }

    @Step
    public static void shouldBeLoggedOut() {
        onSite();
        if (userName.isDisplayed()) {
            logout();
        }
        if (loginForm.isDisplayed()) {
            userIcon.click();
        }
    }

    @Step
    public static void logout() {
        if (!logout.isDisplayed()) {
            userIcon.click();
        }
        logout.click();
    }
}
