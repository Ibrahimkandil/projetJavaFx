package com.mycompany.atmmanagementsys.utils;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private final String URL = "jdbc:mysql://localhost:3306/Bank";
    private final String USER = "root";
    private final String PASSWORD = "0000";
    private Connection connection;
    private static DbConnection instance;
    private DbConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public static DbConnection getInstance() {
        if(instance == null)
            instance = new DbConnection();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connection Connection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "0000");
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
