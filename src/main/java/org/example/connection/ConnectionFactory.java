package org.example.connection;

import java.sql.*;
//java database connectivity
public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/orders_management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "YOUR_PASSWORD";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            throw new RuntimeException("Connection failed", e);
        }
    }
}
