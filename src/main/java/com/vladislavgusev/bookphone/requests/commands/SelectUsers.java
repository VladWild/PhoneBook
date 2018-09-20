package com.vladislavgusev.bookphone.requests.commands;

import com.vladislavgusev.bookphone.data.User;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import com.vladislavgusev.bookphone.printers.Printer;
import com.vladislavgusev.bookphone.reports.Report;
import com.vladislavgusev.bookphone.requests.Command;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SelectUsers implements Command {
    private static final Logger logger = Logger.getLogger(SelectUsers.class);

    private UserDAO userDAO;

    public SelectUsers(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void execute(Printer printer, Report report) throws SQLException, IOException {
        logger.debug("Get all \"Users\" from \"UserDAO\"");
        List<User> users = userDAO.selectAllUsers();
        logger.debug("Print report on getting all users from \"UserDAO\"");
        printer.print(report.getReportAllUsers(users));
    }
}
