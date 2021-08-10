package com.epam.tc.utils;

import org.testng.annotations.DataProvider;

public class DivideDataProvider {
    @DataProvider(name = "correct divide data")
    public static Object[][] correctData() {
        return new Object[][]{
            {6, 3, 2},
            {-2, 2, -1},
            {45, -5, -9},
            {Long.MAX_VALUE, 1, Long.MAX_VALUE}};
    }

    @DataProvider(name = "small divide data")
    public static Object[][] bigData() {
        return new Object[][]{
            {1E-10, 1E10, 1E-20},
            {0.5, 1E-20, 5E19},
            {Long.MAX_VALUE, 0.1, 9.223372036854776E19}};
    }

    @DataProvider(name = "double divide data")
    public static Object[][] doubleData() {
        return new Object[][]{
            {3.6, 3, 1.2},
            {1, 9.5, 0.1052631578947}};
    }
}
