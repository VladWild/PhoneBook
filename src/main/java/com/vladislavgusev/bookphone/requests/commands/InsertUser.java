package com.vladislavgusev.bookphone.requests.commands;

import com.vladislavgusev.bookphone.data.User;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import com.vladislavgusev.bookphone.printers.Printer;
import com.vladislavgusev.bookphone.reports.Report;
import com.vladislavgusev.bookphone.requests.Command;

import java.io.IOException;
import java.sql.SQLException;

public class InsertUser implements Command {
    private User user;
    private UserDAO userDAO;

    public InsertUser(User user, UserDAO userDAO) {
        this.user = user;
        this.userDAO = userDAO;
    }

    @Override
    public void execute(Printer printer, Report report) throws IOException, SQLException {
        int primaryKey = userDAO.insertUser(user);
        printer.print(report.getReportInsertUser(user, primaryKey));
    }
}
