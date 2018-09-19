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
import java.sql.Connection;
import java.sql.SQLException;

public class PhoneBook {
    private static final Logger LOGGER = Logger.getLogger(PhoneBook.class);

    private String[] request;

    private PhoneBook(String[] request) {
        this.request = request;
    }

    private void run() throws IOException {
        LOGGER.info("Initializing the printer for output");
        Printer printer = FactoryPrinters.getTypePrinter(FactoryPrinters.CONSOLE);

        DBType dbType = DBType.ORACLE;
        LOGGER.info("Create a connection to the database");
        try (Connection connection = DAOFactory.getInstance(dbType)
                .getConnection()) {
            LOGGER.info("Initialization of the input command parsing");
            Parser parser = new CommandParser(request);

            LOGGER.info("Get an object DataBase from factory");
            DAOFactory factory = DAOFactory.getInstance(dbType);     //?  2-ой раз
            LOGGER.info("Get an UserDAO for manipulating database data");
            UserDAO userDAO = factory.getUserDAO(connection);

            LOGGER.info("Initializing the report");
            Report report = new SimpleReport();

            LOGGER.info("Initializing the required command by request");
            Command command = RequestInvoker.getCommand(parser, userDAO);
            LOGGER.info("Execute the required command");
            command.execute(printer, report);
        } catch (CommandException e) {
            LOGGER.error(e.getDeveloperMessage());
            printer.print(e.getUserMessage());
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("A system exception was thrown: Exception: " + e.toString());
            printer.printError();          // ? как лучше
        }
    }

    public static void main(String[] args) throws IOException {
        LOGGER.info("Application \"Phone Book\" is start");
        PhoneBook phoneBook = new PhoneBook(args);

        LOGGER.debug("Application \"Payment Systems\" is run");
        phoneBook.run();
    }
}

