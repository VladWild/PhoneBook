package com.vladislavgusev.bookphone.datalayer;

import com.vladislavgusev.bookphone.data.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    int insertUser(User user) throws SQLException;
    List<User> selectAllUsers() throws SQLException;
}
