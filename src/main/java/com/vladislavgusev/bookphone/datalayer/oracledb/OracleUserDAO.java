package com.vladislavgusev.bookphone.datalayer.oracledb;

import com.vladislavgusev.bookphone.data.User;
import com.vladislavgusev.bookphone.datalayer.UserDAO;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class OracleUserDAO implements UserDAO {
    private static final Logger logger = Logger.getLogger(OracleUserDAO.class);

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
        logger.debug("Get \"Insert\" annotation for getting a request to a data base");
        Insert request = (Insert) getAnnotationWithRequest(Insert.class);

        logger.debug("Get \"PreparedStatement\" with a request from the annotation");
        PreparedStatement ps = connection.prepareStatement(request.request(),
                new String[]{ "ID" });
        logger.debug("Set parameter \"Name\" in request");
        ps.setString(1, user.getName());
        logger.debug("Set parameter \"Number\" in request");
        ps.setString(2, user.getNumber());
        logger.debug("Update data in data base");
        ps.executeUpdate();

        logger.debug("Get \"ResultSet\" for getting id user");
        ResultSet rs = ps.getGeneratedKeys();
        Integer id = null;

        logger.debug("Get \"id\" user from \"ResultSet\"");
        if(rs.next()){
            id = rs.getInt(1);
        }

        logger.debug("Close \"ResultSet\"");
        rs.close();
        logger.debug("Close \"PreparedStatement\"");
        ps.close();

        return id != null ? id.intValue() : 0;
    }

    @Override
    @Select(request = "SELECT id, name, phonenumber FROM PHONEBOOK")
    public List<User> selectAllUsers() throws SQLException {
        List<User> records = new ArrayList<>();

        logger.debug("Get \"Select\" annotation for getting a request to a data base");
        Select request = (Select) getAnnotationWithRequest(Select.class);

        logger.debug("Get \"Statement\" to send a request");
        Statement statement = connection.createStatement();
        logger.debug("Get \"ResultSet\" by request for a getting results");
        ResultSet result = statement.executeQuery(request.request());
        while (result.next()){
            records.add(new User(result.getInt(1),
                    result.getString(2),
                    result.getString(3)));
        }

        logger.debug("Close \"ResultSet\"");
        result.close();
        logger.debug("Close \"PreparedStatement\"");
        statement.close();

        return records;
    }
}

