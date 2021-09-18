package com.epam.tc.hw9.utils;

import com.epam.tc.hw9.exceptions.PropertyFileReaderException;
import java.io.FileReader;
import java.util.Properties;

public class PropertyFileReader {

    public static Properties init(String path) {
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader(path)) {
            // load a properties file
            properties.load(fileReader);
        } catch (Exception ex) {
            throw new PropertyFileReaderException(path);
        }
        return properties;
    }
}
