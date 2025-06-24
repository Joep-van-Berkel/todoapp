package org.example.todoapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {

    private static DbConn instance;
    public Connection conn;

    public DbConn(){
        if (instance == null){
            instance = this;
            makeSqlConnection();
        }
    }

    public static DbConn getInstance() {
        if (instance == null) {
            instance = new DbConn();
        }
        return instance;
    }

    public boolean makeSqlConnection(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/todoapp", "root", "avans");
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            return false;
        }
        return true;
    }

    public boolean closeSqlConnection(){
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("VendorError: " + e.getErrorCode());
            return false;
        }
        return true;
    }

}
