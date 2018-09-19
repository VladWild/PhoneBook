package com.vladislavgusev.bookphone.datalayer.oracledb;

import java.util.ResourceBundle;

public enum  ResourceMenu {
    INSTANCE;
    private final String RESOURCE_NAME = "requests";
    private ResourceBundle resourceBundle;

    ResourceMenu() {
        this.resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

}
