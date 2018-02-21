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

    public User getByName(String userName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE item_name = ?");
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
            //int hourly = 0;

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
                //hourly = rs.getInt("HOURLY");

            }
            user = new User(userName2, addressId, phoneIdList, password, firstname, lastname, role, email, hourlyRate);
            pool.freeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
            //int hourly = 0;

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
                //hourly = rs.getInt("HOURLY");
            }
            user = new User(userName2, addressId, phoneIdList, password, firstname, lastname, role, email, hourlyRate);
            userList.add(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return userList;
    }

    public String insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String result = null;

        try {
            ArrayList phoneIdList = new ArrayList();
            PreparedStatement pstmt = connection.prepareStatement("select insert_user_func(?, ?, ?, ?)");
            pstmt.setString(1, user.getUsername());
            pstmt.setInt(2, user.getAddress());

            for (int i = 0; i < phoneIdList.size(); i++) {
                pstmt.setInt(3, user.getPhone().get(i));
            }
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getFirstName());
            pstmt.setString(6, user.getLastName());
            pstmt.setString(7, user.getRole());
            pstmt.setString(8, user.getEmail());
            pstmt.setInt(9, user.getHourlyRate());
            //pstmt.setInt(10, user.getHours());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result = rs.getString(1);
            }
            pool.freeConnection(connection);

            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return result;
    }

    public String update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            ArrayList phoneIdList = new ArrayList();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE user SET "
                    + "ADDRESS_ID = ?, PHONE_ID = ?, PASSWORD = ?, FIRSTNAME = ?, LASTNAME =?, "
                    + "ROLE = ?, EMAIL = ?, HOURLYRATE =? WHERE USER_NAME =?");

            pstmt.setInt(2, user.getAddress());

            for (int i = 0; i < phoneIdList.size(); i++) {
                pstmt.setInt(3, user.getPhone().get(i));
            }
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getFirstName());
            pstmt.setString(6, user.getLastName());
            pstmt.setString(7, user.getRole());
            pstmt.setString(8, user.getEmail());
            pstmt.setInt(9, user.getHourlyRate());
            //pstmt.setInt(10, user.getHours());

            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "updated";
    }

    public String delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            ArrayList phoneIdList = new ArrayList();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM user WHERE USER_NAME = ?");
            pstmt.setString(1, user.getUsername());

            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "deleted";
    }
}
