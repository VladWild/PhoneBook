package com.vladislavgusev.bookphone.parsers.command.exeptions;

public class WrongCommandGet extends CommandException{
    private static final String DEVELOPER = "Wrong command: get";
    private static final String USER = "You entered the wrong command: get";

    public WrongCommandGet(){
        super(DEVELOPER);
    }

    @Override
    public String getDeveloperMessage() {
        return DEVELOPER;
    }

    @Override
    public String getUserMessage() {
        return USER;
    }
}
