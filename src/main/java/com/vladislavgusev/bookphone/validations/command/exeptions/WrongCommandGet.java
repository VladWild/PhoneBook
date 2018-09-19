package com.vladislavgusev.bookphone.validations.command.exeptions;

public class WrongCommandGet extends RuntimeException{
    private static final String MESSAGE = "Wrong command: set";

    public WrongCommandGet(){
        super(MESSAGE);
    }

}
