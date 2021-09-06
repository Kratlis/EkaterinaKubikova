package com.epam.tc.hw7.utils;

import com.epam.tc.hw7.entities.MetalsColorsFormData;

public class Utils {
    public static final String JSON_PROPERTY_FILE_PATH = "src/test/resources/metalsColorsDataSet.json";

    public static String getExpectedSummaryResult(MetalsColorsFormData formData) {
        return String.format("Summary: %s",
            Integer.parseInt(formData.summary[0]) + Integer.parseInt(formData.summary[1]));
    }

    public static String getExpectedElementsResult(MetalsColorsFormData formData) {
        String res = formData.elements[0];
        for (int i = 1; i < formData.elements.length; i++) {
            res = String.join(", ", res, formData.elements[i]);
        }
        return String.format("Elements: %s", res);
    }

    public static String getExpectedColorResult(MetalsColorsFormData formData) {
        return String.format("Color: %s", formData.color);
    }

    public static String getExpectedMetalResult(MetalsColorsFormData formData) {
        return String.format("Metal: %s", formData.metals);
    }

    public static String getExpectedVegetablesResult(MetalsColorsFormData formData) {
        String res = formData.vegetables[0];
        for (int i = 1; i < formData.vegetables.length; i++) {
            res = String.join(", ", res, formData.vegetables[i]);
        }
        return String.format("Vegetables: %s", res);
    }
}
