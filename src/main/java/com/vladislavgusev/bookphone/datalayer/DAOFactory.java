package com.vladislavgusev.bookphone.datalayer;

import java.sql.SQLException;

public abstract class DAOFactory {

    public static DAOFactory getInstance(DBType dbType) throws SQLException, ClassNotFoundException {
        return dbType.getDAOFactory();
    }

    public abstract UserDAO getUserDAO();
}
