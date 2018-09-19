package com.vladislavgusev.bookphone.validations.command.exeptions;

public class WrongCommandSet extends RuntimeException {
    private static final String MESSAGE = "Wrong command: set";

    public WrongCommandSet(){
        super(MESSAGE);
    }
}
