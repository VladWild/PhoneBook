package com.vladislavgusev.bookphone.reports;

import com.vladislavgusev.bookphone.data.User;

import java.util.List;

public class SimpleReport implements Report {
    private static final String INSERT_INFO = "The following entry was added:\n\n";
    private static final String SELECT_INFO = "All records:\n\n";

    private static final String LINE = "+-----+--------------------+---------------+\n";
    private static final String HEAD = "| ID  | NAME               | PHONE         |\n";
    private static final String USER = "| %s | %s | %s |\n";
    private static final String ENTER = "\n";
    private static final String DELIMITER = "|";
    private static final String SPACE = " ";

    private static final int ID_COUNT = 3;
    private static final int NAME_COUNT = 18;
    private static final int PHONE_COUNT = 13;

    private String getSpacesByCount(int count){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < count; i++){
            str.append(SPACE);
        }
        return str.toString();
    }

    private String getRecord(User user) {
        StringBuilder str = new StringBuilder();
        str.append(String.format(USER,
                user.getId() + getSpacesByCount(ID_COUNT - String.valueOf(user.getId()).length()),
                user.getName() + getSpacesByCount(NAME_COUNT - user.getName().length()),
                user.getNumber() + getSpacesByCount(PHONE_COUNT - user.getNumber().length())));
        return str.toString();
    }

    @Override
    public String getReportInsertUser(User user, int pk) {
        StringBuilder str = new StringBuilder();
        str.append(INSERT_INFO);
        str.append(LINE);
        str.append(HEAD);
        str.append(LINE);
        str.append(getRecord(user));
        str.append(LINE);
        return str.toString();
    }

    @Override
    public String getReportAllUsers(List<User> users) {
        StringBuilder str = new StringBuilder();
        str.append(SELECT_INFO);
        str.append(LINE);
        str.append(HEAD);
        str.append(LINE);
        users.forEach(user -> str.append(getRecord(user)));
        str.append(LINE);
        return str.toString();
    }
}
