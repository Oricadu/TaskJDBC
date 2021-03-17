package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDaoJDBCImpl userService = new UserDaoJDBCImpl();

        userService.createUsersTable();

        userService.saveUser("Barack ", "Obama", (byte) 59);
        userService.saveUser("Michelle", "Obama", (byte) 57);
        userService.saveUser("Malia", "Obama", (byte) 22);
        userService.saveUser("Sasha", "Obama", (byte) 19);

        for (User user : userService.getAllUsers()) {
            System.out.println(user.toString());
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
