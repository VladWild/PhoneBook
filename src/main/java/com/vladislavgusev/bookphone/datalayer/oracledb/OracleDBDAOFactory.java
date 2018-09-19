package com.vladislavgusev.bookphone.datalayer.oracledb;

import com.vladislavgusev.bookphone.datalayer.DAOFactory;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import com.vladislavgusev.bookphone.utilities.ResourceMenu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class OracleDBDAOFactory extends DAOFactory{
    private static OracleDBDAOFactory instance;

    private final ResourceMenu manager = new ResourceMenu();

    private OracleDBDAOFactory() throws IOException {

    }

    public static OracleDBDAOFactory getInstance() throws IOException {
        if (instance == null) {
            instance = new OracleDBDAOFactory();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        String url = manager.getString("url");
        String user = manager.getString("user");
        String password = manager.getString("password");
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public UserDAO getUserDAO(Connection connection) {
        return new OracleUserDAO(connection);
    }
}
