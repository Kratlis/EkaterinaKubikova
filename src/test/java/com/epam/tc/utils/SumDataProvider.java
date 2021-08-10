package com.epam.tc.utils;

import org.testng.annotations.DataProvider;

public class SumDataProvider {
    @DataProvider(name = "correct sum data")
    public static Object[][] correctData() {
        return new Object[][]{
            {2, 2, 4},
            {1, 4, 5}};
    }

    @DataProvider(name = "big sum data")
    public static Object[][] bigData() {
        return new Object[][]{
            {Long.MAX_VALUE, 1, -9223372036854775808L}};
    }

    @DataProvider(name = "negative sum data")
    public static Object[][] negativeData() {
        return new Object[][]{
            {2, -2, 0},
            {-1, 9.5, 8.5}};
    }

}
