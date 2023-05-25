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

        String sql = "CREATE TABLE IF NOT EXISTS USER" +
                "(id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "lastName VARCHAR(50)," +
                "age TINYINT DEFAULT 0)";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.printf("При создании таблицы произошла ошибка!");
        }
    }

    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS USER";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.printf("При удалении таблицы произошла ошибка!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO USER (name, lastName, age) VALUES(?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.printf("Не удалось добавить пользователя!");
        }
    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM USER WHERE id=?";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Не удалось удалить пользователя!");
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USER";

        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.printf("Произошла ошибка при выполнении запроса в БД (Select*From)!");
        }
        return users;
    }

    public void cleanUsersTable() {

        String sql = "DELETE FROM USER";
        try (Statement statement = Util.getConnection().createStatement()) {

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.printf("При удалении записей из таблицы возникла ошибка!");
        }

    }
}
