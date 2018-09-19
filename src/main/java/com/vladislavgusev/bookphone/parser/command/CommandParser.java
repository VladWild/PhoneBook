package com.vladislavgusev.bookphone.parser.command;

import com.vladislavgusev.bookphone.parser.command.exeptions.*;
import com.vladislavgusev.bookphone.requests.CommandType;

public class CommandParser implements Parser {
    private CommandType commandType;
    private String name;
    private String number;

    public CommandParser(String[] command){
        validation(command);                        //Exception в конструкторе ?
        initCommand(command[0]);
        initName(commandType, command);
        initNumber(commandType, command);
    }

    private boolean isDigit(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void validation(String[] command) {
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

    private void initCommand(String command){
        commandType = command.equals("set") ? CommandType.INSERT : CommandType.SELECT;
    }

    private void initName(CommandType commandType, String[] command){
        name = commandType == CommandType.INSERT ? command[1] : null;
    }

    private void initNumber(CommandType commandType, String[] command){
        number = commandType == CommandType.INSERT ? command[2] : null;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getNumber() {
        return number;
    }
}
