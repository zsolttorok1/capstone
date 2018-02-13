/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author 725899
 */
public class CapstoneDB {   
        private static final String userName = "root";
        private static final String password = "password";
        
        private static final String dbms = "mysql";
        private static final String serverName = "localhost";
        private static final String portNumber = "3306";
        private static final String databaseName = "CapstoneDB";
    
        //TODO create connection pool rather.
        public static Connection getConnection() {
            Properties connectionProps = new Properties();
            connectionProps.put("user", userName);
            connectionProps.put("password", password);
            
            try {
		Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL JDBC Driver?");
                e.printStackTrace();
                return null;
            }

//            System.out.println("MySQL JDBC Driver Registered!");
            Connection connection = null;

            try {
                connection = DriverManager.getConnection(
                        "jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + databaseName,
                    connectionProps);

            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return null;
            }

            if (connection != null) {
//                System.out.println("Connection established.");
            } else {
                System.out.println("Failed to make connection!");
            }

            return connection;
        }
}
