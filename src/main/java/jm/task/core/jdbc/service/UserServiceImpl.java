package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userService = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userServiceHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {

//        userService.createUsersTable();
        userServiceHibernate.createUsersTable();
    }

    public void dropUsersTable() {

//        userService.dropUsersTable();
        userServiceHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {

//        userService.saveUser(name, lastName, age);
        userServiceHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {

//        userService.removeUserById(id);
        userServiceHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {

//        return userService.getAllUsers();
        return userServiceHibernate.getAllUsers();
    }

    public void cleanUsersTable() {

//        userService.cleanUsersTable();
        userServiceHibernate.cleanUsersTable();
    }
}
