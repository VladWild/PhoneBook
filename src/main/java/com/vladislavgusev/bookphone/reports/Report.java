package com.vladislavgusev.bookphone.reports;

import com.vladislavgusev.bookphone.data.User;

import java.util.List;

public interface Report {
    String getReportInsertUser(User user, int pk);
    String getReportAllUsers(List<User> records);
}
