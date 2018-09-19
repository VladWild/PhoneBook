package com.vladislavgusev.bookphone.printers;

public enum FactoryPrinters {
    CONSOLE {
        @Override
        protected Printer getPrinter() {
            return new ConsolePrinter();
        }
    }, FILE {
        @Override
        protected Printer getPrinter() {
            return new FilePrinter();
        }
    };

    protected abstract Printer getPrinter();

    public static Printer getTypePrinter(FactoryPrinters printerType){
        return printerType.getPrinter();
    }
}

