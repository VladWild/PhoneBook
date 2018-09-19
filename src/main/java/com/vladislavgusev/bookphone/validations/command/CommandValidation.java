package com.vladislavgusev.bookphone.validations.command;

import com.vladislavgusev.bookphone.validations.Validation;
import com.vladislavgusev.bookphone.validations.command.exeptions.*;

public class CommandValidation implements Validation {
    private String[] command;

    public CommandValidation(String[] command){
        this.command = command;
    }

    private static boolean isDigit(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void isValidate() {
        if (command.length == 0){
            throw new EmptyRequest();
        }
        if (!command[0].equals("set") && !command[0].equals("get")) {
            throw new UnknownCommand(command[0]);
        }
        if (command[0].equals("set") && command.length != 3){
            throw new WrongCommandSet();
        }
        if (command[0].equals("set") && !isDigit(command[2])){
            throw new WrongNumber(command[2]);
        }
        if (command[0].equals("get") && command.length != 1){
            throw new WrongCommandGet();
        }
    }
}
