package com.epam.tc.hw1.utils;

import org.testng.annotations.DataProvider;

public class MultiplyDataProvider {
    @DataProvider(name = "correct multiply data")
    public static Object[][] correctData() {
        return new Object[][]{
            {1, 3, 3},
            {0, 0, 0},
            {0, -5, 0},
            {-1, 1, -1},
            {6, -3, -18},
            {-4, -9, 36}};
    }

    @DataProvider(name = "big multiply data")
    public static Object[][] bigDoubleData() {
        return new Object[][]{
            {Long.MAX_VALUE, 1, Long.MAX_VALUE},
            {Long.MAX_VALUE, -1, -Long.MAX_VALUE}};
    }

    @DataProvider(name = "double multiply data")
    public static Object[][] doubleData() {
        return new Object[][]{
            {1.3, 9, 11.7},
            {8.7, -9.5, -82.65},
            {-6, 4.4, -26.4}};
    }
}
