package com.escuela.util;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/escuela";
    private static final String USER = "root";
    private static final String PASS = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static boolean testConnection() {
        try (Connection c = getConnection()) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}