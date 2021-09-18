package com.epam.tc.hw9.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    public static Properties init(String path) {
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader(path)) {
            // load a properties file
            properties.load(fileReader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}
