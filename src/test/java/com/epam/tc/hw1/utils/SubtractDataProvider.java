package com.epam.tc.hw1.utils;

import org.testng.annotations.DataProvider;

public class SubtractDataProvider {
    @DataProvider(name = "correct subtract data")
    public static Object[][] correctData() {
        return new Object[][]{
            {5, 3, 2},
            {-2, 4, -6},
            {4, -5, 9}};
    }

    @DataProvider(name = "big subtract data")
    public static Object[][] bigData() {
        return new Object[][]{
            {-Long.MAX_VALUE, 1, -9223372036854775808L}};
    }

    @DataProvider(name = "double subtract data")
    public static Object[][] doubleData() {
        return new Object[][]{
            {3.6, 0.3, 3.3},
            {1, 9.5, -8.5}};
    }
}
