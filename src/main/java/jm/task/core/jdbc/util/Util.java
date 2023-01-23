package jm.task.core.jdbc.util;

//import org.hibernate.SessionBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    //    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "rootroot";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection OK");
//            conn.setAutoCommit(false);
//            conn.commit();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        } finally {
            if (conn != null) {
                //        conn.setAutoCommit(false);
                //        conn.close();
                System.out.println("Соединение с БД закрыто");
            }
        }

        return conn;
    }


}
