package com.vladislavgusev.bookphone.parsers.command.exeptions;

public class EmptyRequest extends CommandException {
    private static final String DEVELOPER = "Entered empty request";
    private static final String USER = "You entered an empty query";

    public EmptyRequest(){
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
