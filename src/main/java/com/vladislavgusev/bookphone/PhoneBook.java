package com.vladislavgusev.bookphone;

import com.vladislavgusev.bookphone.datalayer.DAOFactory;
import com.vladislavgusev.bookphone.datalayer.DBType;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import com.vladislavgusev.bookphone.parser.command.CommandParser;
import com.vladislavgusev.bookphone.parser.command.Parser;
import com.vladislavgusev.bookphone.printers.FactoryPrinters;
import com.vladislavgusev.bookphone.printers.Printer;
import com.vladislavgusev.bookphone.reports.Report;
import com.vladislavgusev.bookphone.reports.SimpleReport;
import com.vladislavgusev.bookphone.requests.Command;
import com.vladislavgusev.bookphone.requests.RequestInvoker;
import com.vladislavgusev.bookphone.parser.command.exeptions.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class PhoneBook {
    private static final Logger LOGGER = Logger.getLogger(PhoneBook.class);

    private static final Printer printer =
            FactoryPrinters.getTypePrinter(FactoryPrinters.CONSOLE);

    private String[] request;

    private PhoneBook(String[] request) {
        this.request = request;
    }

    private void run() throws IOException, SQLException, ClassNotFoundException {
        Parser parser = new CommandParser(request);

        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();

        Report report = new SimpleReport();

        Command command = RequestInvoker.getCommand(request, userDAO);
        command.execute(printer, report);
    }

    public static void main(String[] args) throws IOException {
        LOGGER.info("Application \"Phone Book\" is start");
        PhoneBook phoneBook = new PhoneBook(args);
        try {
            LOGGER.debug("Application \"Payment Systems\" is run");
            phoneBook.run();
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("A system exception was thrown: Exception: " + e.toString());
            printer.printError();          // ? как лучше
        } catch (CommandException e) {
            LOGGER.error(e.getDeveloperMessage());
            printer.print(e.getUserMessage());
        }
    }
}

