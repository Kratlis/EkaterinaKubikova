package com.epam.tc.hw9;

import com.epam.tc.hw9.utils.PropertyFileReader;
import java.util.Properties;

public class InitProperties {

    public static final String PROPERTY_FILE = "src/test/resources/hw9_api/test.properties";
    public static final Properties AUTH_PROPERTIES = PropertyFileReader.init(PROPERTY_FILE);

    public static final String AUTH_KEY = AUTH_PROPERTIES.getProperty("key");
    public static final String AUTH_TOKEN = AUTH_PROPERTIES.getProperty("token");
}
