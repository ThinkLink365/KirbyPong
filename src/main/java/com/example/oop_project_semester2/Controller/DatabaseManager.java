/**
 * The DatabaseManager class provides methods to manage the database connection
 * and perform various database operations.
 */
package com.example.oop_project_semester2.Controller;

import java.sql.*;

public class DatabaseManager {
    // Database connection parameters
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
    private static final String USERNAME = "root"; // Replace with your database username
    private static final String PASSWORD = "Pokemon13!"; // Replace with your database password

    private static DatabaseManager instance = null;
    private Connection connection = null;

    /**
     * Private constructor to prevent instantiation from outside.
     * Initializes the database connection.
     */
    private DatabaseManager() {
        try {
            // Step 1: Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish the connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of DatabaseManager.
     * If the instance doesn't exist or the existing connection is closed, creates a new instance.
     * @return The DatabaseManager instance.
     */
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        } else {
            try {
                // Check if the existing connection is valid
                if (instance.getConnection() == null || instance.getConnection().isClosed()) {
                    instance = new DatabaseManager();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * Gets the database connection.
     * @return The Connection object representing the database connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Closes the database connection.
     */
    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
