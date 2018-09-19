package com.vladislavgusev.bookphone.datalayer.oracledb;

import com.vladislavgusev.bookphone.datalayer.DAOFactory;
import com.vladislavgusev.bookphone.datalayer.UserDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class OracleDBDAOFactory extends DAOFactory{
    private static volatile OracleDBDAOFactory instance;
    private Connection connection;

    private OracleDBDAOFactory() {

    }

    public static OracleDBDAOFactory getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            synchronized (OracleDBDAOFactory.class) {
                instance = new OracleDBDAOFactory();
                instance.connected();
            }
        }
        return instance;
    }

    private void connected() throws ClassNotFoundException, SQLException {
        Locale.setDefault(Locale.ENGLISH);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "SYSTEM";
        String password = "1111";
        connection = DriverManager.getConnection(url, user, password);

        //System.out.println("Connected to oracle DB!");
    }

    @Override
    public UserDAO getUserDAO() {
        return new OracleUserDAO(connection);
    }
}
