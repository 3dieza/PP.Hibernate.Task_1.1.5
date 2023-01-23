package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection conn = Util.getConnection();
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = conn.createStatement()) {
            String sqlCommand = "CREATE TABLE IF NOT exists User " +
                    "(id INTEGER AUTO_INCREMENT not NULL  , " +
                    " name VARCHAR(255), " +
                    " lastName VARCHAR(255), " +
                    " age TINYINT, " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sqlCommand);
            System.out.println("Database has been created!");
        } catch (SQLException e) {
            System.out.println("Database has NOT bee created(already exist");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()) {
            String sqlCommand = ("DROP TABLE IF exists User ");
            statement.executeUpdate(sqlCommand);
//            System.out.println("Database has been dropped!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO User (name, lastName, age) " +
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
        try (Connection connection = Util.getConnection()) {
            String sql = "DELETE FROM User WHERE  User.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM User";
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
        try (Connection conn = Util.getConnection();
             Statement statement = conn.createStatement()) {
            String sqlCommand = ("TRUNCATE table User ");
            statement.executeUpdate(sqlCommand);
            System.out.println("Database has been truncated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
