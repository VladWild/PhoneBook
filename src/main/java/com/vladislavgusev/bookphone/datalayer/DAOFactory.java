package com.vladislavgusev.bookphone.datalayer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {

    public static DAOFactory getInstance(DBType dbType) throws SQLException, ClassNotFoundException, IOException {
        return dbType.getDAOFactory();
    }

    public abstract Connection getConnection() throws SQLException;

    public abstract UserDAO getUserDAO(Connection connection);
}
