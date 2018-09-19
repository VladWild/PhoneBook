package com.vladislavgusev.bookphone.printers;

import java.io.IOException;

public interface Printer {
    String ERROR = "This application does not work at the moment. Contact your administrator";

    void print(String text) throws IOException;

    default void printError() throws IOException {
        print(ERROR);
    }
}
