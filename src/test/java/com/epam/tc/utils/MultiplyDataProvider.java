package com.epam.tc.utils;

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
    public static Object[][] bigData() {
        return new Object[][]{
            {Long.MAX_VALUE, 1, Long.MAX_VALUE},
            {Long.MAX_VALUE, -1, -Long.MAX_VALUE},
            {-1, Long.MIN_VALUE, -9223372036854775808L},
            {Long.MAX_VALUE, 2, 1.8446744E19}};
    }

    @DataProvider(name = "double multiply data")
    public static Object[][] doubleData() {
        return new Object[][]{
            {1.3, 9, 11.7},
            {8.7, -9.5, -82.65},
            {-6, 4.4, -26.4}};
    }
}
