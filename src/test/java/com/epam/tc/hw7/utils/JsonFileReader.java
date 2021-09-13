package com.epam.tc.hw7.utils;

import com.epam.tc.hw7.entities.MetalsColorsFormData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

public class JsonFileReader {

    public static LinkedHashMap<String, MetalsColorsFormData> readMetalsColorsFileDataSet(String path)
        throws FileNotFoundException {
        Gson gson = new Gson();
        Type empMapType = new TypeToken<LinkedHashMap<String, MetalsColorsFormData>>() {
        }.getType();
        Reader reader = new FileReader(path);
        return gson.fromJson(reader, empMapType);
    }
}
