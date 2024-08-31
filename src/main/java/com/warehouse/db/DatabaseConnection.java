package com.warehouse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null || isClosed()) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:warehouse.db");
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                System.out.println("Connection to SQLite failed: " + e.getMessage());
            }
        }
        return connection;
    }

    private static boolean isClosed() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            System.out.println("Failed to check if connection is closed: " + e.getMessage());
            return true;
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close database connection: " + e.getMessage());
            }
        }
    }
}
