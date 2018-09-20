package com.vladislavgusev.bookphone.requests.commands;

import com.vladislavgusev.bookphone.data.User;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import com.vladislavgusev.bookphone.printers.Printer;
import com.vladislavgusev.bookphone.reports.Report;
import com.vladislavgusev.bookphone.requests.Command;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class InsertUser implements Command {
    private static final Logger logger = Logger.getLogger(InsertUser.class);

    private User user;
    private UserDAO userDAO;

    public InsertUser(User user, UserDAO userDAO) {
        this.user = user;
        this.userDAO = userDAO;
    }

    @Override
    public void execute(Printer printer, Report report) throws IOException, SQLException {
        logger.debug("Insert user by object \"User\" in \"UserDAO\" and get \"Primary Key\" of user");
        int primaryKey = userDAO.insertUser(user);
        logger.debug("Set \"id\" in \"User\"");
        user.setId(primaryKey);
        logger.debug("Print report on insert user in \"UserDAO\"");
        printer.print(report.getReportInsertUser(user, primaryKey));
    }
}
