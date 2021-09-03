package com.epam.tc;

import com.epam.jdi.light.elements.common.UIElement;
import com.epam.jdi.light.elements.complex.Menu;
import com.epam.jdi.light.elements.composite.Form;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.Css;
import com.epam.jdi.light.elements.pageobjects.annotations.locators.UI;
import com.epam.tc.entities.User;
import com.epam.tc.pages.HomePage;
import com.epam.tc.pages.MetalsColorsPage;
import org.openqa.selenium.WebElement;

//@JSite("https://jdi-testing.github.io/jdi-light/")
public class SiteJdi {
    public static HomePage homePage;
    public static MetalsColorsPage metalsColorsPage;
    @Css("form") public static Form<User> loginForm;

    @Css(".profile-photo [ui=label]") public static UIElement userName;
    @Css(".logout") public static WebElement logout;
    @Css("img#user-icon") public static UIElement userIcon;
    @UI(".nav li") public static Menu header;

}
