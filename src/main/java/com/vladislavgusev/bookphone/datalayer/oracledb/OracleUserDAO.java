package com.vladislavgusev.bookphone.datalayer.oracledb;

import com.vladislavgusev.bookphone.data.User;
import com.vladislavgusev.bookphone.datalayer.UserDAO;

import java.lang.annotation.Annotation;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class OracleUserDAO implements UserDAO {
    private Connection connection;

    private OracleUserDAO(){

    }

    OracleUserDAO(Connection connection){
        this.connection = connection;
    }

    private Annotation getAnnotationWithRequest(Class<? extends Annotation> annotation){
        return Arrays.stream(getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .map(method -> method.getAnnotation(annotation))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    @Insert(request = "INSERT INTO PhoneBook VALUES (seq_id.NEXTVAL, ?, ?)")
    public int insertUser(User user) throws SQLException {
        Insert request = (Insert) getAnnotationWithRequest(Insert.class);

        PreparedStatement ps = connection.prepareStatement(request.request(),
                new String[]{ "ID" });
        ps.setString(1, user.getName());
        ps.setString(2, user.getNumber());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        Integer id = null;

        if(rs.next()){
            id = rs.getInt(1);
        }

        rs.close();
        ps.close();

        return id != null ? id.intValue() : 0;
    }

    @Override
    @Select(request = "SELECT id, name, phonenumber FROM PHONEBOOK")
    public List<User> selectAllUsers() throws SQLException {
        List<User> records = new ArrayList<>();
        Select request = (Select) getAnnotationWithRequest(Select.class);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(request.request());
        while (result.next()){
            records.add(new User(result.getInt(1),
                    result.getString(2),
                    result.getString(3)));
        }

        result.close();
        statement.close();

        return records;
    }
}

