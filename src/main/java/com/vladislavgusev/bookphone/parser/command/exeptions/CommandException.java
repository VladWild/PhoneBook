package com.vladislavgusev.bookphone.parser.command.exeptions;

public abstract class CommandException extends RuntimeException {
    CommandException(String DEVELOPER){
        super(DEVELOPER);
    }

    public abstract String getDeveloperMessage();
    public abstract String getUserMessage();
}
