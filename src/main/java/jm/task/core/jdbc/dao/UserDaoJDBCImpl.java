package jm.task.core.jdbc.dao;

import com.mysql.cj.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.transaction.*;
import javax.transaction.xa.XAResource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection conn = Util.getConnection();
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

             Statement statement = conn.createStatement()){
            String sqlCommand = ("CREATE TABLE IF NOT exists User777 " +
                    "(id INTEGER PRIMARY KEY AUTO_INCREMENT not NULL  , " +
                    " name VARCHAR(255), " +
                    " astName VARCHAR(255), " +
                    " age TINYINT)");
            statement.executeUpdate(sqlCommand);
            System.out.println("Database has been created!");
        } catch (SQLException e) {
            System.out.println("Database has NOT bee created(already exist");
           e.printStackTrace();
        }
    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO User777 (name, lastName, age) " +
                            "VALUE (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM User777";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {

    }
}
