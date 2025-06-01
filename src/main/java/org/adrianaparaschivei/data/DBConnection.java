package org.adrianaparaschivei.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ELearningDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    private DBConnection() {
        connect();
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut realiza conexiunea cu baza de date!", e);
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Eroare la verificarea conexiunii!", e);
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Nu s-a putut realiza inchiderea bazei de date!", e);
        }
    }
}
