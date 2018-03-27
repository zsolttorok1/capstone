/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class PCRBroker {
    private static PCRBroker pcrBroker;
    private ConnectionPool pool;
    private Connection connection;
    
    public static PCRBroker getInstance() {
        if (pcrBroker == null) {
            pcrBroker = new PCRBroker();
        }
        
        return pcrBroker;
    }
    
    private PCRBroker() {
    }
    
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

    //returns "PasswordChangeRequest, null"
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

    //returns "inserted, updated, error, exception"
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
    
    //returns "deleted, error, exception"
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
