package com.vladislavgusev.bookphone.validations.command.exeptions;

public class EmptyRequest extends RuntimeException {
    private static final String MESSAGE = "Entered empty request";

    public EmptyRequest(){
        super(MESSAGE);
    }
}
