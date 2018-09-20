package com.vladislavgusev.bookphone.utilities;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ResourceMenu {
    private static final String NAME_PROPERTY_FILE = "settings.properties";

    private static ResourceMenu resourceMenu;

    private Properties properties;

    private ResourceMenu() throws IOException {
        this.properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream(NAME_PROPERTY_FILE));
    }

    public static synchronized ResourceMenu getInstance() throws IOException {
        if (Objects.isNull(resourceMenu)){
            resourceMenu = new ResourceMenu();
        }
        return resourceMenu;
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }
}
