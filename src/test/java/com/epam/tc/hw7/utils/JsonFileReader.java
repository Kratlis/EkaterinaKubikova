package com.epam.tc.hw7.utils;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class JsonFileReader {

    public static MetalsColorsDataSet readMetalsColorsFileDataSet(String path) throws FileNotFoundException {
        Gson gson = new Gson();
        Reader reader = new FileReader(path);
        return gson.fromJson(reader, MetalsColorsDataSet.class);
    }
}
