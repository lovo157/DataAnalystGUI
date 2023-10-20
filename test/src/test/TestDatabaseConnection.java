package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDatabaseConnection {

    public static void main(String[] args) {
        testConnection();
    
    }

    public static void testConnection() {
        String dbPath = "jdbc:sqlite:C:/Users/Isaac/Desktop/db/mydb";
        
        try {
            // Explicitly load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Try connecting to the database
            Connection connection = DriverManager.getConnection(dbPath);
            System.out.println("Connection successful!");

            // If connection is successful, retrieve some data
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT title, author FROM titles LIMIT 5");
            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title") + ", Author: " + resultSet.getString("author"));
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

