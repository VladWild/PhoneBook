package com.vladislavgusev.bookphone.printers;

import com.vladislavgusev.bookphone.utilities.ResourceMenu;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FilePrinter implements Printer{
    private final ResourceMenu manager = new ResourceMenu();

    FilePrinter() throws IOException {

    }

    @Override
    public void print(String text) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(manager.getString("output_file_path")), StandardCharsets.UTF_8))) {
            writer.write(text.toCharArray());
        }
    }
}
