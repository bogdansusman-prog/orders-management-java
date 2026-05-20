package org.example.connection;

import java.sql.*;
/**
 * Clasa responsabila pentru realizarea conexiunii
 * la baza de date PostgreSQL utilizata de aplicatie.
 */
public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/orders_management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "YOUR_PASSWORD";

    /**
     * Creeaza si returneaza o conexiune la baza de date.
     *
     * @return conexiunea realizata la baza de date
     * @throws RuntimeException daca conexiunea esueaza
     */
    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            throw new RuntimeException("Connection failed", e);
        }
    }
}
