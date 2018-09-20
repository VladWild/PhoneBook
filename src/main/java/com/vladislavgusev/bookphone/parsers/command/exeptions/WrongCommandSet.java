package com.vladislavgusev.bookphone.parsers.command.exeptions;

public class WrongCommandSet extends CommandException {
    private static final String DEVELOPER = "Wrong command: set";
    private static final String USER = "You entered the wrong command: set";

    public WrongCommandSet(){
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
