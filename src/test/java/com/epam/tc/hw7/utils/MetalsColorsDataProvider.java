package com.epam.tc.hw7.utils;

import com.epam.tc.hw7.entities.MetalsColorsFormData;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import org.testng.annotations.DataProvider;

public class MetalsColorsDataProvider {

    @DataProvider(name = "form fields")
    public static Object[][] formData() throws FileNotFoundException {
        //        MetalsColorsFormData[] metalsColorsFormData = readData();
        LinkedHashMap<String, MetalsColorsFormData> map = readData();
        String[] keys = map.keySet().toArray(new String[0]);
        Object[][] data = new Object[keys.length][];
        for (int i = 0; i < keys.length; i++) {
            data[i] = new Object[] {map.get(keys[i])};
        }
        /*
        ArrayList list = readData().keySet().toArray();
        for (int i = 0; i < metalsColorsFormData.length; i++) {
            data[i] = new Object[] {metalsColorsFormData[i]};
        }*/
        return data;
    }

    private static LinkedHashMap<String, MetalsColorsFormData> readData() throws FileNotFoundException {
        ArrayList<MetalsColorsFormData> formDataList = new ArrayList<>();
        //ArrayList<MetalsColorsFormData> set = JsonFileReader.readMetalsColorsFileDataSet(
        // Utils.JSON_PROPERTY_FILE_PATH);
        /*formDataList.add(set.data1);
        formDataList.add(set.data2);
        formDataList.add(set.data3);
        formDataList.add(set.data4);
        formDataList.add(set.data5);*/
        return JsonFileReader.readMetalsColorsFileDataSet(Utils.JSON_PROPERTY_FILE_PATH);
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(readData().get(readData().keySet().toArray()[1]));
        //        System.out.println(readData()[2].metals);
    }
}
