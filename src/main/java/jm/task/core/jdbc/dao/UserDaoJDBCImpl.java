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

        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS `users` (" +
                            "  `ID` BIGINT(8) NOT NULL AUTO_INCREMENT," +
                            "  `name` VARCHAR(45) NULL," +
                            "  `lastName` VARCHAR(45) NULL," +
                            "  `age` TINYINT(1) NULL," +
                            "  PRIMARY KEY (`ID`));");
            //System.out.println("table created successfully");
        } catch (SQLException e) {
            System.err.println("Failed to create table\n" + e.getMessage());
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(
                    "DROP TABLE IF EXISTS `users`;");
            System.out.println("table dropped successfully");
        } catch (SQLException e) {
            System.err.println("Failed to drop table\n" + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO users(`name`, lastname, age) VALUES(?, ?, ?);";

        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

            System.out.printf("User %s added successfully\n", name);

        } catch (SQLException e) {
            System.err.println("Failed to add user\n" + e.getMessage());
        }
    }

    public void removeUserById(long id) {

        String query = "DELETE FROM users WHERE ID = ?;";

        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            System.out.println("user removed successfully");

        } catch (SQLException e) {
            System.err.println("Failed to remove user\n" + e.getMessage());
        }

    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM `users`";

        try (Connection connection = Util.openConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Failed to get a list users\n" + e.getMessage());

        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("TRUNCATE TABLE `users`");

            System.out.println("table cleaned successfully");

        } catch (SQLException e) {
            System.err.println("Failed to clean table\n" + e.getMessage());
        }
    }
}
