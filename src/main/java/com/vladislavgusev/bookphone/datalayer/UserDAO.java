package com.vladislavgusev.bookphone.datalayer;

import com.vladislavgusev.bookphone.data.Record;
import com.vladislavgusev.bookphone.data.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    int insertUser(User user) throws SQLException;
    List<Record> selectAllUsers() throws SQLException;
}
