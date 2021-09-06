package com.epam.tc.hw3.ex2;

import org.testng.annotations.DataProvider;

public class DifferentElementsPageDataProvider {

    @DataProvider(name = "selected option, radio, checkboxes")
    public static Object[][] elementsData() {
        return new Object[][] {
            {"Yellow", "Selen", "Water", "Wind"}
        };
    }

    @DataProvider(name = "selected checkboxes")
    public static Object[][] checkboxesData() {
        return new Object[][] {
            {"Water", "Wind"}
        };
    }

    @DataProvider(name = "selected radioButton")
    public static Object[][] radioButtonData() {
        return new Object[][] {
            {"Selen"}
        };
    }

    @DataProvider(name = "selected dropdown option")
    public static Object[][] dropdownOptionData() {
        return new Object[][] {
            {"Yellow"}
        };
    }
}
