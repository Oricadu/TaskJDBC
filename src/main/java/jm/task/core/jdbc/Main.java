package jm.task.core.jdbc;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static final String URL = "jdbc:mysql://localhost:3307/usersdatabase?autoReconnect=true&useSSL=false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "111111";

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Connection connection;

        try {

            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD);
            if (!connection.isClosed()) {
                connection.close();
            }

            System.out.println("Connection successful");

        } catch (SQLException e) {
            System.err.println("Connection failed");
        }
    }
}
