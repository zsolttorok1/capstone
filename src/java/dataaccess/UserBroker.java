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
    private static UserBroker userBroker;
    private ConnectionPool pool;
    private Connection connection;
    
    public static UserBroker getInstance() {
        if (userBroker == null) {
            userBroker = new UserBroker();
        }
        
        return userBroker;
    }
    
    private UserBroker() {
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

    //returns "User, null"
    public User getByName(String userName) {
        getConnection();
        
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT u.user_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, p.phone_number, u.password, u.firstname, u.lastname, r.role_name, u.email, u.hourly_rate" 
                    + "     FROM `user` u" 
                    + "     JOIN `phone_user` up ON up.user_name = u.user_name" 
                    + "     JOIN `phone` p ON p.phone_id = up.phone_id" 
                    + "     JOIN `address` a ON u.address_id = a.address_id" 
                    + "     JOIN `role` r ON r.role_id = u.role_id" 
                    + "     WHERE u.user_name = ?;");
            pstmt.setString(1, userName);
            ResultSet rs = pstmt.executeQuery();
        
            int houseNumber = 0;
            String street = null;
            String city = null;
            String province = null;
            String country = null;
            String postalCode = null;
            int phoneNumber = 0;
            List<Integer> phoneNumberList = new ArrayList<>();
            String password = null;
            String firstname = null;
            String lastname = null;
            String role = null;
            String email = null;
            int hourlyRate = 0;
            //int hourly = 0;

            while (rs.next()) {
                houseNumber = rs.getInt("HOUSE_NUMBER");
                street = rs.getString("STREET");
                city = rs.getString("CITY");
                province = rs.getString("PROVINCE");
                country = rs.getString("COUNTRY");
                postalCode = rs.getString("POSTAL_CODE");
                phoneNumber = rs.getInt("PHONE_NUMBER");
                phoneNumberList.add(phoneNumber); //adds all phone numbers to the phoneNumberList
                password = rs.getString("PASSWORD");
                firstname = rs.getString("FIRSTNAME");
                lastname = rs.getString("LASTNAME");
                role = rs.getString("ROLE_NAME");
                email = rs.getString("EMAIL");
                hourlyRate = rs.getInt("HOURLY_RATE");
                //hourly = rs.getInt("HOURLY");
            }
            user = new User(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstname, lastname, role, email, hourlyRate);
 
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return user;
    }

    //returns "List of Users, empty List, null"
    public List<User> getAll() {
        getConnection();

        List<User> userList = new ArrayList<>();
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT u.user_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, p.phone_number, u.password, u.firstname, u.lastname, r.role_name, u.email, u.hourly_rate" 
                    + "     FROM `user` u" 
                    + "     JOIN `phone_user` up ON up.user_name = u.user_name" 
                    + "     JOIN `phone` p ON p.phone_id = up.phone_id" 
                    + "     JOIN `address` a ON u.address_id = a.address_id" 
                    + "     JOIN `role` r ON r.role_id = u.role_id;");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("USER_NAME");
                int houseNumber = rs.getInt("HOUSE_NUMBER");
                String street = rs.getString("STREET");
                String city = rs.getString("CITY");
                String province = rs.getString("PROVINCE");
                String country = rs.getString("COUNTRY");
                String postalCode = rs.getString("POSTAL_CODE");
                int phoneNumber = rs.getInt("PHONE_NUMBER");
                String password = rs.getString("PASSWORD");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                String role = rs.getString("ROLE_NAME");
                String email = rs.getString("EMAIL");
                int hourlyRate = rs.getInt("HOURLY_RATE");
                //hourly = rs.getInt("HOURLY");
                
                //if userList isEmpty, or last user = current user
                if (userList.isEmpty() || !((User)userList.get(userList.size()-1)).getFirstName().equals(userName)) {
                    List<Integer> phoneNumberList = new ArrayList<>();
                    
                    user = new User(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstName, lastName, role, email, hourlyRate);
                    userList.add(user);
                }
                
                if (userList != null ) {
                    user.getPhoneNumberList().add(phoneNumber);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return userList;
    }

    //returns "inserted, updated, error, exception"
    public String insert(User user) {
        getConnection();
        
        String status = null;

        try {
            for (int phoneNumber : user.getPhoneNumberList()) {
                PreparedStatement pstmt = connection.prepareStatement("select insert_user_func(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt.setString(1, user.getUsername());
                pstmt.setInt(2, user.getHouseNumber());
                pstmt.setString(3, user.getStreet());
                pstmt.setString(4, user.getCity());
                pstmt.setString(5, user.getProvince());
                pstmt.setString(6, user.getCountry());
                pstmt.setString(7, user.getPostalCode());
                pstmt.setInt(8, phoneNumber);
                pstmt.setString(9, user.getPassword());
                pstmt.setString(10, user.getFirstName());
                pstmt.setString(11, user.getLastName());
                pstmt.setString(12, user.getRole());
                pstmt.setString(13, user.getEmail());
                pstmt.setInt(14, user.getHourlyRate());
                //pstmt.setInt(10, user.getHours());

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
            }
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

    //returns "updated, error, exception"
    public String update(User user) {
        String returnValue = insert(user);
      
        if (returnValue != null && (returnValue.contains("updated") || returnValue.contains("inserted"))) {
            return "updated";
        }
        
        return returnValue; //expection, error
    }

    //returns "deleted, error, exception"
    public String delete(User user) {
        getConnection();
        
        String status = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement("select delete_user_func(?)");
            pstmt.setString(1, user.getUsername());

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
}
