package com.example.testmock.controllers;

import com.example.testmock.model.User;

import java.sql.*;


public class DBController {
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String URL = "jdbc:postgresql://172.24.0.4:5432/postgres_docker";

    public static User getUserByLogin(String log) throws SQLException {
        Connection connection = null;
        User user = new User();
        String request = "select login, password ,date, email from users\n" +
                "join mail on login_id =users.login\n" +
                "where login='" + log + "'";
        try {
            connection = DriverManager.getConnection(DBController.URL, DBController.USERNAME, DBController.PASSWORD);
            Statement statement;
            statement = connection.createStatement();
            ResultSet result1 = statement.executeQuery(request);

            while (result1.next()) {
                user.setLogin(result1.getString("login"));
                user.setPassword(result1.getString("password"));
                user.setDate(result1.getDate("date").toLocalDate());
                user.setEmail(result1.getString("email"));
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new SQLException("Пользователя с таким логином не существует!");
        }
        return user;
    }

    public static int insertUserToDB(User user) throws SQLException {
        int rowsNum = 0;
        String request = "insert into users (login, password,date) VALUES(?,?,?) ON CONFLICT (login) DO UPDATE SET password=EXCLUDED.password, date=EXCLUDED.date ;\n" +
                "insert into mail (login_id,email) VALUES(?,?) ON CONFLICT (login_id) DO UPDATE SET email=EXCLUDED.email ;";

        try (Connection connection = DriverManager.getConnection(DBController.URL, DBController.USERNAME, DBController.PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(request)
        ) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, Date.valueOf(user.getDate()));
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getEmail());
            rowsNum += preparedStatement.executeUpdate();

        }
        return rowsNum;
    }
}
