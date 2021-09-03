package com.epam.tc.hw7.utils;

import com.epam.tc.entities.MetalsColorsFormData;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import org.testng.annotations.DataProvider;

public class MetalsColorsDataProvider {

    @DataProvider(name = "form fields")
    public static Object[][] formData() throws FileNotFoundException {
        ArrayList<MetalsColorsFormData> formDataList = readData();
        return new Object[][] {
            {formDataList.get(0)},
            {formDataList.get(1)},
            {formDataList.get(2)},
            {formDataList.get(3)},
            {formDataList.get(4)}
        };
    }

    private static ArrayList<MetalsColorsFormData> readData() throws FileNotFoundException {
        ArrayList<MetalsColorsFormData> formDataList = new ArrayList<>();
        MetalsColorsDataSet set = JsonFileReader.readMetalsColorsFileDataSet(Utils.JSON_PROPERTY_FILE_PATH);
        formDataList.add(set.data1);
        formDataList.add(set.data2);
        formDataList.add(set.data3);
        formDataList.add(set.data4);
        formDataList.add(set.data5);
        return formDataList;
    }

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(Arrays.toString(readData().get(1).vegetables));
        System.out.println(readData().get(2).metals);
    }
}
