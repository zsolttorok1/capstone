/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import database.ConnectionPool;
import domainmodel.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserBroker {
    public User getByName(String userName){
       ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       User user = null;
       try{
           PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE item_name ?");
            pstmt.setString(1, userName);
            
            ResultSet rs = pstmt.executeQuery();
            ArrayList phoneIdList = new ArrayList();
            
            String userName2 = null;
            int addressId = 0;
            int phoneId = 0;
            String password = null; 
            String firstname = null;
            String lastname = null;
            String role = null; 
            String email = null;
            int hourlyRate = 0;
            int hourly= 0;
            
            while(rs.next()){
                userName2 = rs.getString("USER_NAME");
                addressId = rs.getInt("ADDRESS_ID");
                phoneId = rs.getInt("PHONE_ID");
                phoneIdList.add(phoneId);//add the phone number to the arraylist every single
                password = rs.getString("PASSWORD");
                firstname = rs.getString("FIRSTNAME");
                lastname = rs.getString("LASTNAME");
                role = rs.getString("ROLE");
                email = rs.getString("EMAIL");
                hourlyRate = rs.getInt("HOURLYRATE");
                hourly = rs.getInt("HOURLY");
                        
            }
            user = new User(userName2, addressId, phoneIdList, password, firstname, lastname, role, email, hourlyRate, hourly);
            pool.freeConnection(connection);
       }catch (SQLException ex){
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
       }
       finally{
           pool.freeConnection(connection);
       }
       return user;
    }
    
    public List<User> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        List<User> userList = new ArrayList<>();
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user");

            ResultSet rs = pstmt.executeQuery();
            
            ArrayList phoneIdList = new ArrayList();
            
            String userName2 = null;
            int addressId = 0;
            int phoneId = 0;
            String password = null; 
            String firstname = null;
            String lastname = null;
            String role = null; 
            String email = null;
            int hourlyRate = 0;
            int hourly= 0;
            
            while (rs.next()) {
                userName2 = rs.getString("USER_NAME");
                addressId = rs.getInt("ADDRESS_ID");
                phoneId = rs.getInt("PHONE_ID");
                phoneIdList.add(phoneId);//add the phone number to the arraylist every single
                password = rs.getString("PASSWORD");
                firstname = rs.getString("FIRSTNAME");
                lastname = rs.getString("LASTNAME");
                role = rs.getString("ROLE");
                email = rs.getString("EMAIL");
                hourlyRate = rs.getInt("HOURLYRATE");
                hourly = rs.getInt("HOURLY");
            }
            user = new User(userName2, addressId, phoneIdList, password, firstname, lastname, role, email, hourlyRate, hourly);
            userList.add(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            pool.freeConnection(connection);
        }
                
        return userList;
    }
   
}
