package com.epam.tc.hw3.ex2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {
    private final String name = "name";
    private final String password = "password";
    private final String username = "username";
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
        return properties.getProperty(name);
    }

    public String readPassword() {
        return properties.getProperty(password);
    }

    public String readUsername() {
        return properties.getProperty(username);
    }
}
