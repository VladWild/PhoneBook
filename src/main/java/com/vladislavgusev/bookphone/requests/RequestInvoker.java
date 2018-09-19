package com.vladislavgusev.bookphone.requests;

import com.vladislavgusev.bookphone.data.User;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import com.vladislavgusev.bookphone.requests.commands.InsertUser;
import com.vladislavgusev.bookphone.requests.commands.SelectUsers;

public abstract class RequestInvoker {
    public static Command getCommand(String[] request, UserDAO userDAO){
        if (request[0].equals("set")) {
            return new InsertUser(new User(request[1], request[2]), userDAO);
        } else {
            return new SelectUsers(userDAO);
        }
    }
}



