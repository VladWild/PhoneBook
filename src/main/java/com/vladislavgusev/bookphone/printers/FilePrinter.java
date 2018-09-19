package com.vladislavgusev.bookphone.printers;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class FilePrinter implements Printer{
    private static final String PATH_FILE = "src//main//resources//output";

    @Override
    public void print(String text) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(PATH_FILE), StandardCharsets.UTF_8))) {
            writer.write(text.toCharArray());
        }
    }
}
