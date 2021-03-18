package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        String query = "CREATE TABLE IF NOT EXISTS `users` (" +
                "  `ID` BIGINT(8) NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NULL," +
                "  `lastName` VARCHAR(45) NULL," +
                "  `age` TINYINT(1) NULL," +
                "  PRIMARY KEY (`ID`));";

        queryWithTransaction(session -> session.getSession().createSQLQuery(query).executeUpdate(),
                "table created successfully",
                "Failed to create table");

    }

    @Override
    public void dropUsersTable() {

        String query = "DROP TABLE IF EXISTS `users`;";

        queryWithTransaction(session -> session.createSQLQuery(query).executeUpdate(),
                "table dropped successfully",
                "Failed to drop table");

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        queryWithTransaction(session -> session.save(new User(name, lastName, age)),
                String.format("User %s added successfully\n", name),
                "Failed to add user");
    }

    @Override
    public void removeUserById(long id) {

        queryWithTransaction(session -> session.createQuery("delete User where id = " + id).executeUpdate(),
                "user removed successfully",
                "Failed to remove user");
    }

    @Override
    public List<User> getAllUsers() {

        return queryWithTransaction((session) -> session.createQuery("from User").list(),
                "",
                "Failed to get a list users");





    }

    @Override
    public void cleanUsersTable() {

        String query = "TRUNCATE TABLE `users`";

        queryWithTransaction(session -> session.createSQLQuery(query).executeUpdate(),
                "Table cleaned successfully",
                "Failed to clean table");

    }


    private <T> T queryWithTransaction(Function<Session, T> function,
                                            String successMessage,
                                            String failedMessage) {
        T result = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()){

            session.beginTransaction();

            result = function.apply(session);

            session.getTransaction().commit();

            System.out.println(successMessage);
        } catch (HibernateException e) {
            System.err.println(failedMessage);
            System.err.println(e.getMessage());

        }

        return result;
    }


}
