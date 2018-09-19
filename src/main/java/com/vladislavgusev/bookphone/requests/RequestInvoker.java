package com.vladislavgusev.bookphone.requests;

import com.vladislavgusev.bookphone.data.User;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import com.vladislavgusev.bookphone.parser.command.Parser;
import com.vladislavgusev.bookphone.requests.commands.InsertUser;
import com.vladislavgusev.bookphone.requests.commands.SelectUsers;

public abstract class RequestInvoker {
    public static Command getCommand(Parser parser, UserDAO userDAO){
        if (parser.getCommandType() == CommandType.INSERT) {
            return new InsertUser(new User(parser.getName(), parser.getNumber()), userDAO);
        } else {
            return new SelectUsers(userDAO);
        }
    }
}



