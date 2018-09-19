package com.vladislavgusev.bookphone.printers;

import java.io.IOException;

public interface Printer {
    void print(String text) throws IOException;
}
