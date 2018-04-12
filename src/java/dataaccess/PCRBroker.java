package dataaccess;

import database.ConnectionPool;
import domainmodel.PasswordChangeRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DataConverter;

/**
 * Broker class which communicates with JDBC and handles all the Password Change Recovery information management.
 */
public class PCRBroker {
    /**
     * contains the PCRBroker instance
     */
    private static PCRBroker pcrBroker;
    
    /**
     * Reference to the ConnectionPool
     */
    private ConnectionPool pool;
    
    /**
     * Reference to the Connection
     */
    private Connection connection;
    
    /**
     * Singleton method which gets the PCRBroker instance.
     * @return the PCRBroker instance
     */
    public static PCRBroker getInstance() {
        if (pcrBroker == null) {
            pcrBroker = new PCRBroker();
        }
        
        return pcrBroker;
    }
    
    /**
     * Default constructor
     */
    private PCRBroker() {
    }
    
    /**
     * Gets the next free connection from the connection pool.
     * @return "ok" if everything went fine, "connection error" if there was an issue in the process.
     */
    private String getConnection() {
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            
            if (connection != null) {
                connection.setAutoCommit(false);
                return "ok";
            }
            
            else {
                return "connection error";
            }
            
        } catch (Exception ex) {
            Logger.getLogger(PCRBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "connection error";
        }
    }

    /**
     * Gets the PasswordChangeRequest from the pcrUUID string
     * @param pcrUUID the uuid string
     * @return the found PasswordChangeRequest, or null if unexpected issue occurred.
     */
    public PasswordChangeRequest getByUUID(String pcrUUID) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        PasswordChangeRequest passwordChangeRequest = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                + "SELECT pcr.pcr_uuid, pcr.pcr_time, pcr.user_name "
                + "     FROM `password_change_request` pcr "
                + "     JOIN `user` u ON u.user_name = pcr.user_name "
                + "WHERE pcr.pcr_uuid = ?; ");
            pstmt.setString(1, pcrUUID);
            ResultSet rs = pstmt.executeQuery();
       
            if (rs.next()) {
                String uuid = rs.getString("PCR_UUID");
                Date pcrTime = rs.getDate("PCR_TIME");
                String userName = rs.getString("USER_NAME");
                
                passwordChangeRequest = new PasswordChangeRequest(pcrUUID, pcrTime, userName);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return passwordChangeRequest;
    }

    /**
     * Inserts the PasswordChangeRequest object information to the database
     * @param pcr the PasswordChangeRequest object
     * @return "inserted" if everything went ok, "error" if there was an expected issue, "exception" if there was an unexpected issue.
     */
    public String insert(PasswordChangeRequest pcr) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("select insert_pcr_func(?, ?, ?)");
            pstmt.setString(1, pcr.getPcrUUID());
            pstmt.setDate(2, DataConverter.javaDateToSQL(pcr.getPcrTime()));
            pstmt.setString(3, pcr.getUserName());
            
            ResultSet rs = pstmt.executeQuery();
            //get the status report from current database function
            while (rs.next()) {
                status = rs.getString(1);
            }
            //if something unexpected happened, rollback any changes.
            if (status == null || status.equals("error")) {
                connection.rollback();
                return "error";
            }
 
            //if all good, commit
            connection.commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                return "exception";
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return "exception";
        } finally {
            pool.freeConnection(connection);
        }

        return status;
    }
    
    /**
     * Deletes the PasswordChangeRequest object information from the database
     * @param pcr the PasswordChangeRequest object to delete
     * @return "deleted" if everything went ok, "error" if there was an expected issue, "exception" or null if there was an unexpected issue.
     */
    public String delete(PasswordChangeRequest pcr) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("select delete_pcr_func(?)");
            pstmt.setString(1, pcr.getPcrUUID());
            
            ResultSet rs = pstmt.executeQuery();
            //get the status report from current database function
            while (rs.next()) {
                status = rs.getString(1);
            }
            //if something unexpected happened, rollback any changes.
            if (status == null || status.equals("error")) {
                connection.rollback();
                return "error";
            }
 
            //if all good, commit
            connection.commit();
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                return "exception";
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return "exception";
        } finally {
            pool.freeConnection(connection);
        }

        return status;
    }
}
