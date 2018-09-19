package com.vladislavgusev.bookphone.utilities;

import java.io.IOException;
import java.util.Properties;

public class ResourceMenu {
    private Properties properties;

    public ResourceMenu() throws IOException {
        this.properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }
}
