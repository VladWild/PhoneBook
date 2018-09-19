package com.vladislavgusev.bookphone.parser.command.exeptions;

public class UnknownCommand extends CommandException{
    private static final String DEVELOPER = "Entered unknown command: ";
    private static final String USER = "You entered an unknown command: ";

    private String command;

    public UnknownCommand(String command){
        super(DEVELOPER);
        this.command = command;
    }

    @Override
    public String getDeveloperMessage() {
        return DEVELOPER + command;
    }

    @Override
    public String getUserMessage() {
        return USER + command;
    }
}
