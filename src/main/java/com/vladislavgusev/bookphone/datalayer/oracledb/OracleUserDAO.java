package com.vladislavgusev.bookphone.datalayer.oracledb;

import com.vladislavgusev.bookphone.data.Record;
import com.vladislavgusev.bookphone.data.User;
import com.vladislavgusev.bookphone.datalayer.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleUserDAO implements UserDAO {
    private static ResourceMenu manager = ResourceMenu.INSTANCE;
    private Connection connection;

    private OracleUserDAO(){

    }

    OracleUserDAO(Connection connection){
        this.connection = connection;
    }

    @Override

    public int insertUser(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(manager.getString("insert"),
                new String[]{ "ID" });
        ps.setString(1, user.getName());
        ps.setString(2, user.getNumber());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int id = 0;

        if(rs.next()){
            id = rs.getInt(1);
        }
        rs.close();

        ps.close();

        connection.close(); //?

        return id;
    }

    @Override
    public List<Record> selectAllUsers() throws SQLException {
        List<Record> records = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(manager.getString("select"));
        while (result.next()){
            records.add(new Record(result.getInt(1),
                    new User(result.getString(2), result.getString(3))));
        }

        result.close();
        statement.close();

        connection.close();  //?

        return records;
    }
}

