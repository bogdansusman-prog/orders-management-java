package org.example.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Clasa responsabila pentru realizarea conexiunii
 * la baza de date PostgreSQL utilizata de aplicatie.
 * <p>
 * Datele de conectare se citesc din fisierul {@code config.properties}
 * (din directorul curent sau din classpath) sau din variabilele de mediu
 * {@code DB_URL}, {@code DB_USER}, {@code DB_PASSWORD}.
 */
public class ConnectionFactory {
    private static final Properties CONFIG = loadConfig();
    private static final String URL = get("db.url", "DB_URL", "jdbc:postgresql://localhost:5432/orders_management");
    private static final String USER = get("db.user", "DB_USER", "postgres");
    private static final String PASSWORD = get("db.password", "DB_PASSWORD", "");

    private static Properties loadConfig() {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream("config.properties")) {
            props.load(in);
            return props;
        } catch (IOException ignored) {
            // fall back to classpath
        }
        try (InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException ignored) {
            // fall back to environment variables / defaults
        }
        return props;
    }

    private static String get(String key, String envVar, String defaultValue) {
        String env = System.getenv(envVar);
        if (env != null && !env.isEmpty()) {
            return env;
        }
        return CONFIG.getProperty(key, defaultValue);
    }

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
