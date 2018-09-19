package com.vladislavgusev.bookphone.datalayer;

import com.vladislavgusev.bookphone.datalayer.oracledb.OracleDBDAOFactory;

import java.sql.SQLException;

public enum DBType {
    ORACLE {
        @Override
        public DAOFactory getDAOFactory() throws SQLException, ClassNotFoundException {
            return OracleDBDAOFactory.getInstance();
        }
    };

    public abstract DAOFactory getDAOFactory() throws SQLException, ClassNotFoundException;
}
