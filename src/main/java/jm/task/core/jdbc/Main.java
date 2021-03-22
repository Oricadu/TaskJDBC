package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь


        UserDaoHibernateImpl userServiceHibernate = new UserDaoHibernateImpl();

        userServiceHibernate.createUsersTable();

        userServiceHibernate.saveUser("Barack ", "Obama", (byte) 59);
        userServiceHibernate.saveUser("Michelle", "Obama", (byte) 57);
        userServiceHibernate.saveUser("Malia", "Obama", (byte) 22);
        userServiceHibernate.saveUser("Sasha", "Obama", (byte) 19);


        for (User user : userServiceHibernate.getAllUsers()) {
            System.out.println(user.toString());
        }

        userServiceHibernate.cleanUsersTable();

        userServiceHibernate.dropUsersTable();

        Util.closeSessionFactory();






    }
}
