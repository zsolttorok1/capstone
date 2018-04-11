/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import database.ConnectionPool;
import domainmodel.Quote;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuoteBroker {

    private static QuoteBroker quoteBroker;
    private ConnectionPool pool;
    private Connection connection;

    public static QuoteBroker getInstance() {
        if (quoteBroker == null) {
            quoteBroker = new QuoteBroker();
        }

        return quoteBroker;
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
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "connection error";
        }
    }

    public String insert(Quote quote) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }

        try {
            PreparedStatement pstmt = connection.prepareStatement("select insert_quote_func(?, ?, ?)");
            pstmt.setString(1, quote.getName());
            pstmt.setString(2, quote.getEmail());
            pstmt.setString(3, quote.getDescription());
           

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
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                return "exception";
            } catch (SQLException ex1) {
                Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return "exception";
        } finally {
            pool.freeConnection(connection);
        }

        return status;
    }

    public String delete(Quote deletedQuote) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }

        try {
            PreparedStatement pstmt = connection.prepareStatement("select delete_quote_func(?)");
            pstmt.setInt(1, deletedQuote.getQuoteId());
            
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
            else {
                connection.commit();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex1);
                return "exception";
            }
            return "exception";
        } finally {
            pool.freeConnection(connection);
        }
        
        return status;
    }
        
    public Quote getById(int quoteId) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        Quote quote = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM quote WHERE quote_id = ?;");
            
            pstmt.setInt(1, quoteId);
            ResultSet rs = pstmt.executeQuery();

            String quoteName = null;
            String email = null;
            String description = null;

            while (rs.next()) {
                quoteName = rs.getString("QUOTE_NAME");
                email = rs.getString("EMAIL");
                description = rs.getString("DESCRIPTION");
            }
            quote = new Quote(quoteId, quoteName, email, description);
 
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        
        return quote;  
    }

    public List<Quote> getAll() {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        List<Quote> quoteList = new ArrayList<>();
        
        try {            
            Quote quote = null;
            
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM QUOTE");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next() && rs.getInt("quote_id") > 0) {
                int quoteId = rs.getInt("quote_id");
                String name = rs.getString("quote_name");
                String email = rs.getString("email");
                String description = rs.getString("description");
                
                
                quote = new Quote(quoteId, name, email, description);
                quoteList.add(quote);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuoteBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            pool.freeConnection(connection);
        }
        
        return quoteList;
    }
}
