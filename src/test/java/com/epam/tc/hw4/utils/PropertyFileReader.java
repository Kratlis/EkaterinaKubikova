package com.epam.tc.hw4.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private Properties properties;

    public PropertyFileReader(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            properties = new Properties();

            // load a properties file
            properties.load(fileReader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String readName() {
        return properties.getProperty(NAME);
    }

    public String readPassword() {
        return properties.getProperty(PASSWORD);
    }

    public String readUsername() {
        return properties.getProperty(USERNAME);
    }
}
