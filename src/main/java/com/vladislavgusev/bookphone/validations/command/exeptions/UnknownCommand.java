package com.vladislavgusev.bookphone.validations.command.exeptions;

public class UnknownCommand extends RuntimeException{
    private static final String MESSAGE = "Entered unknown command";
    private String command;

    public UnknownCommand(String command){
        super(MESSAGE);
        this.command = command;
    }

    public String getCommand(){
        return command;
    }
}
