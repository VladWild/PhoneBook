package com.vladislavgusev.bookphone.parsers.command;

import com.vladislavgusev.bookphone.parsers.Parser;
import com.vladislavgusev.bookphone.parsers.command.exeptions.*;
import com.vladislavgusev.bookphone.requests.CommandType;
import org.apache.log4j.Logger;

public class CommandParser implements Parser {
    private static final Logger logger = Logger.getLogger(CommandParser.class);

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
        logger.debug("Checking for an empty query");
        if (command.length == 0){
            throw new EmptyRequest();
        }
        logger.debug("Check for an unknown command");
        if (!command[0].equals("set") && !command[0].equals("get")) {
            throw new UnknownCommand(command[0]);
        }
        logger.debug("Check for an erroneous command SET");
        if (command[0].equals("set") && command.length != 3){
            throw new WrongCommandSet();
        }
        logger.debug("Check for a number phone");
        if (command[0].equals("set") && !isDigit(command[2])){
            throw new WrongNumber(command[2]);
        }
        logger.debug("Check for an erroneous command GET");
        if (command[0].equals("get") && command.length != 1){
            throw new WrongCommandGet();
        }
    }

    private void initCommand(String command){
        logger.debug("Initialization the command type");
        commandType = command.equals("set") ? CommandType.INSERT : CommandType.SELECT;
    }

    private void initName(CommandType commandType, String[] command){
        logger.debug("Initializing the user name");
        name = commandType == CommandType.INSERT ? command[1] : null;
    }

    private void initNumber(CommandType commandType, String[] command){
        logger.debug("Initialization number");
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
