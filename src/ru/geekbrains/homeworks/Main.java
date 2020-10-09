package ru.geekbrains.homeworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        createTable();

    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/logins?serverTimezone=UTC", "root", "");
    }
    public static void createTable() throws SQLException {
        Connection connection = Main.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE clients(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(255) NOT NULL," +
                        "login VARCHAR(255) NOT NULL,"+
                        "password VARCHAR(255) NOT NULL)");
    }
    }

