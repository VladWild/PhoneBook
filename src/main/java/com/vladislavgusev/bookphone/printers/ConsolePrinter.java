package com.vladislavgusev.bookphone.printers;


public class ConsolePrinter implements Printer {

    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
