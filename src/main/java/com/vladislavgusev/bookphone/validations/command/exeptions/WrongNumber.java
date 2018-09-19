package com.vladislavgusev.bookphone.validations.command.exeptions;

public class WrongNumber extends RuntimeException {
    private static final String MESSAGE = "Number is not validate";
    private String number;

    public WrongNumber(String number){
        super(MESSAGE);
        this.number = number;
    }

    public String getNumber(){
        return number;
    }
}
