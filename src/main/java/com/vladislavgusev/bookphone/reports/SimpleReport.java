package com.vladislavgusev.bookphone.reports;

import com.vladislavgusev.bookphone.data.User;

import java.util.List;

public class SimpleReport implements Report {
    private static final String INSERT_INFO = "The following entry was added:\n";
    private static final String SELECT_INFO = "All records:\n";

    private static final String LINE = "+-----+--------------------+---------------+\n";
    private static final String HEAD = "| ID  | NAME               | PHONE         |\n";
    private static final String ENTER = "\n";
    private static final String DELIMITER = "|";
    private static final String SPACE = " ";

    private static final int ID_COUNT = 5;
    private static final int NAME_COUNT = 20;
    private static final int PHONE_COUNT = 15;

    private String getSpacesByCount(int count){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < count; i++){
            str.append(SPACE);
        }
        return str.toString();
    }

    private String getRecord(User user) {
        StringBuilder str = new StringBuilder();
        str.append(DELIMITER);
        str.append(SPACE);
        str.append(user.getId());
        str.append(getSpacesByCount(ID_COUNT - 1 -
                String.valueOf(user.getId()).length() - 1));
        str.append(SPACE);
        str.append(DELIMITER);
        str.append(SPACE);
        str.append(user.getName());
        str.append(getSpacesByCount(NAME_COUNT - 1 -
                user.getName().length() - 1));
        str.append(SPACE);
        str.append(DELIMITER);
        str.append(SPACE);
        str.append(user.getNumber());
        str.append(getSpacesByCount(PHONE_COUNT - 1 -
                String.valueOf(user.getNumber()).length() - 1));
        str.append(SPACE);
        str.append(DELIMITER);
        str.append(ENTER);
        return str.toString();
    }

    @Override
    public String getReportInsertUser(User user, int pk) {
        StringBuilder str = new StringBuilder();
        str.append(INSERT_INFO);
        str.append(ENTER);
        str.append(LINE);
        str.append(HEAD);
        str.append(LINE);
        str.append(DELIMITER);
        str.append(SPACE);
        str.append(pk);
        str.append(getSpacesByCount(ID_COUNT - 1 -
                String.valueOf(pk).length() - 1));
        str.append(SPACE);
        str.append(DELIMITER);
        str.append(SPACE);
        str.append(user.getName());
        str.append(getSpacesByCount(NAME_COUNT - 1 -
                user.getName().length() - 1));
        str.append(SPACE);
        str.append(DELIMITER);
        str.append(SPACE);
        str.append(user.getNumber());
        str.append(getSpacesByCount(PHONE_COUNT - 1 -
                String.valueOf(user.getNumber()).length() - 1));
        str.append(SPACE);
        str.append(DELIMITER);
        str.append(ENTER);
        str.append(LINE);
        return str.toString();
    }

    @Override
    public String getReportAllUsers(List<User> records) {
        StringBuilder str = new StringBuilder();
        str.append(SELECT_INFO);
        str.append(ENTER);
        str.append(LINE);
        str.append(HEAD);
        str.append(LINE);
        records.forEach(record -> str.append(getRecord(record)));
        str.append(LINE);
        return str.toString();
    }
}
