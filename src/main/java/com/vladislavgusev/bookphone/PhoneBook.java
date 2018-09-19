package com.vladislavgusev.bookphone;

import com.vladislavgusev.bookphone.datalayer.DAOFactory;
import com.vladislavgusev.bookphone.datalayer.DBType;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import com.vladislavgusev.bookphone.printers.FactoryPrinters;
import com.vladislavgusev.bookphone.printers.Printer;
import com.vladislavgusev.bookphone.reports.Report;
import com.vladislavgusev.bookphone.reports.SimpleReport;
import com.vladislavgusev.bookphone.requests.Command;
import com.vladislavgusev.bookphone.requests.RequestInvoker;
import com.vladislavgusev.bookphone.validations.Validation;
import com.vladislavgusev.bookphone.validations.command.CommandValidation;
import com.vladislavgusev.bookphone.validations.command.exeptions.*;

import java.io.IOException;
import java.sql.SQLException;

public class PhoneBook {
    private static final Printer printer =
            FactoryPrinters.getTypePrinter(FactoryPrinters.FILE);
    private static final Report report =
            new SimpleReport();

    private String[] request;

    private PhoneBook(String[] request) {
        this.request = request;
    }

    private void run() throws IOException, SQLException, ClassNotFoundException {
        DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
        UserDAO userDAO = factory.getUserDAO();

        Command command = RequestInvoker.getCommand(request, userDAO);
        command.execute(printer, report);
    }

    public static void main(String[] args) throws IOException {
        try {
            Validation validation = new CommandValidation(args);
            validation.isValidate();
            PhoneBook phoneBook = new PhoneBook(args);
            phoneBook.run();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();


        } catch (UnknownCommand e){
            e.printStackTrace();
            printer.print("You entered an unknown command: " + e.getCommand());
        } catch (EmptyRequest e) {
            e.printStackTrace();
            printer.print("You entered an empty query");
        } catch (WrongCommandSet e){
            e.printStackTrace();
            printer.print("You entered the wrong command: set");
        } catch (WrongNumber e){
            e.printStackTrace();
            printer.print("You entered the wrong number: " + e.getNumber());
        } catch (WrongCommandGet e){
            e.printStackTrace();
            printer.print("You entered the wrong command: set ");
        }
    }
}

