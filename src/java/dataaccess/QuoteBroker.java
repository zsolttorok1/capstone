/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import static database.CapstoneDB.getConnection;
import database.ConnectionPool;
import domainmodel.Quote;
import domainmodel.User;
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

    private void getConnection() {
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String insert(Quote quote) {
        getConnection();
        
        String status = null;

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
         getConnection();
        
        String status = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement("select delete_quote_func(?)");
            pstmt.setString(1, deletedQuote.getName());
            
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
        

    /**
     *
     * @param name
     * @return
     */
    public Quote getByName(String name) {
        getConnection();
        
        Quote quote = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM quote WHERE quote_name = ?;");
            
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            String quote_name = null;
            String email = null;
            String description = null;

            while (rs.next()) {
                quote_name = rs.getString("QUOTE_NAME");
                email = rs.getString("EMAIL");
                description = rs.getString("DESCRIPTION");

            }
            quote = new Quote(quote_name, email, description);
 
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return quote;
        
        
    }

    /**
     *
     * @return
     */
    public List<Quote> getAll() {
        try {
            getConnection();
            
            List<Quote> quoteList = new ArrayList<>();
            Quote quote = null;
            
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM QUOTE");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString("quote_name");
                String email = rs.getString("email");
                String description = rs.getString("description");
                
                
                quote = new Quote(name, email, description);
                quoteList.add(quote);
            }
            
            
            
            return quoteList;// todo
        } catch (SQLException ex) {
            Logger.getLogger(QuoteBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        pool.freeConnection(connection);
        }
        return null;
    }
}
