package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3307/usersdatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "111111";


    public static Connection openConnection() throws SQLException {
        // реализуйте настройку соеденения с БД
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD);

            //System.out.println("Connection successful");

            return connection;
        } catch (
                SQLException e) {
            System.err.println("Connection failed");
            throw e;
        }
    }

}
