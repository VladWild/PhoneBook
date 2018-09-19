package com.vladislavgusev.bookphone.printers;

import java.io.IOException;

public enum FactoryPrinters {
    CONSOLE {
        @Override
        protected Printer getPrinter() {
            return new ConsolePrinter();
        }
    }, FILE {
        @Override
        protected Printer getPrinter() throws IOException {
            return new FilePrinter();
        }
    };

    protected abstract Printer getPrinter() throws IOException;

    public static Printer getTypePrinter(FactoryPrinters printerType) throws IOException {
        return printerType.getPrinter();
    }
}

