package com.epam.tc.hw6.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private static Properties properties;
    PropertyFileReader propertyFileReader;

    public static void init(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            properties = new Properties();

            // load a properties file
            properties.load(fileReader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String readName() {
        return properties.getProperty(NAME);
    }

    public static String readPassword() {
        return properties.getProperty(PASSWORD);
    }

    public static String readUsername() {
        return properties.getProperty(USERNAME);
    }
}
