package com.vladislavgusev.bookphone.parser.command.exeptions;

public class WrongNumber extends CommandException {
    private static final String DEVELOPER = "Number is not validate";
    private static final String USER = "You entered the wrong number: ";

    private String number;

    public WrongNumber(String number){
        super(DEVELOPER);
        this.number = number;
    }

    @Override
    public String getDeveloperMessage() {
        return DEVELOPER;
    }

    @Override
    public String getUserMessage() {
        return USER + number;
    }
}
