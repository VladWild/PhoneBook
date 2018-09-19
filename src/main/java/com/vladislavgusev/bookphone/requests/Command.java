package com.vladislavgusev.bookphone.requests;

import com.vladislavgusev.bookphone.printers.Printer;
import com.vladislavgusev.bookphone.reports.Report;

import java.io.IOException;
import java.sql.SQLException;

public interface Command {
    void execute(Printer printer, Report report) throws IOException, SQLException;
}
