package com.epam.tc.hw7.utils;

import com.epam.tc.hw7.entities.MetalsColorsFormData;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import org.testng.annotations.DataProvider;

public class MetalsColorsDataProvider {

    @DataProvider(name = "form fields")
    public static Object[][] formData() throws FileNotFoundException {
        LinkedHashMap<String, MetalsColorsFormData> map = readData();
        String[] keys = map.keySet().toArray(new String[0]);
        Object[][] data = new Object[keys.length][];
        for (int i = 0; i < keys.length; i++) {
            data[i] = new Object[] {map.get(keys[i])};
        }
        return data;
    }

    private static LinkedHashMap<String, MetalsColorsFormData> readData() throws FileNotFoundException {
        return JsonFileReader.readMetalsColorsFileDataSet(Utils.JSON_PROPERTY_FILE_PATH);
    }
}
