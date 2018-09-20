package com.vladislavgusev.bookphone;

import com.vladislavgusev.bookphone.datalayer.DAOFactory;
import com.vladislavgusev.bookphone.datalayer.DBType;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import com.vladislavgusev.bookphone.parsers.command.CommandParser;
import com.vladislavgusev.bookphone.parsers.Parser;
import com.vladislavgusev.bookphone.parsers.command.exeptions.CommandException;
import com.vladislavgusev.bookphone.printers.FactoryPrinters;
import com.vladislavgusev.bookphone.printers.Printer;
import com.vladislavgusev.bookphone.reports.Report;
import com.vladislavgusev.bookphone.reports.SimpleReport;
import com.vladislavgusev.bookphone.requests.Command;
import com.vladislavgusev.bookphone.requests.RequestInvoker;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class PhoneBook {
    private static final Logger logger = Logger.getLogger(PhoneBook.class);

    private String[] request;

    private PhoneBook(String[] request) {
        this.request = request;
    }

    private void run() throws IOException {
        logger.info("Initializing the printer for output");
        Printer printer = FactoryPrinters.getTypePrinter(FactoryPrinters.CONSOLE);

        DBType dbType = DBType.ORACLE;
        try (Connection connection = DAOFactory.getInstance(dbType)
                .getConnection()) {
            logger.info("Initialization of the input command parsing");
            Parser parser = new CommandParser(request);

            logger.info("Get an object DataBase from factory");
            DAOFactory factory = DAOFactory.getInstance(dbType);     //?  2-ой раз
            logger.info("Get an UserDAO for manipulating database data");
            UserDAO userDAO = factory.getUserDAO(connection);

            logger.info("Initializing the report");
            Report report = new SimpleReport();

            logger.info("Initializing the required command by request");
            Command command = RequestInvoker.getCommand(parser, userDAO);
            logger.info("Execute the required command");
            command.execute(printer, report);
        } catch (CommandException e) {
            logger.error(e.getDeveloperMessage());
            printer.print(e.getUserMessage());
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("A system exception was thrown: Exception: " + e.toString());
            printer.printError();          // ? как лучше
        }
    }

    public static void main(String[] args) throws IOException {
        logger.info("Application \"Phone Book\" is start");
        PhoneBook phoneBook = new PhoneBook(args);

        logger.debug("Application \"Payment Systems\" is run");
        phoneBook.run();
    }
}

