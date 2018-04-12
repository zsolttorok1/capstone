package database;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Connection pool to handle many simultaneous connections or possible DDOS attacks.
 */
public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    /**
     * Constructor
     */
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) 
                ic.lookup("java:/comp/env/jdbc/CapstoneDB");
        } catch (NamingException e) {
            System.out.println(e);
        }
    }


        public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    /**
     * Gets a connection from the connection pool
     * @return a Connection object from the connection pool
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Frees up a connection and returns it to the connection pool
     * @param c the connection to set free
     */
    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}