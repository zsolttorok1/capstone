/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import database.ConnectionPool;
import domainmodel.Customer;
import domainmodel.Item;
import domainmodel.Job;
import domainmodel.Report;
import domainmodel.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DataConverter;
import utilities.HashingUtil;

public class JobBroker {
    private static JobBroker jobBroker;
    private ConnectionPool pool;
    private Connection connection;
    
    public static JobBroker getInstance() {
        if (jobBroker == null) {
            jobBroker = new JobBroker();
        }
        
        return jobBroker;
    }
    
    private JobBroker() {
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

    //returns "Job, null"
    public Job getByName(String jobName) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        Job job = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT j.job_name, a.house_number, a.street, a.city, a.province, a.country, j.customer_id, j.description, j.date_started, j.date_finished, j.balance, j.status "
                    + "     FROM `job` j "
                    + "     JOIN `address` a ON u.address_id = a.address_id "
                    + "     WHERE u.job_name = ?;");
            pstmt.setString(1, jobName);
            ResultSet rs = pstmt.executeQuery();
       
            if (rs.next()) {
                int houseNumber = rs.getInt("HOUSE_NUMBER");
                String street = rs.getString("STREET");
                String city = rs.getString("CITY");
                String province = rs.getString("PROVINCE");
                String country = rs.getString("COUNTRY");
                String postalCode = rs.getString("POSTAL_CODE");
                int customerId = rs.getInt("CUSTOMER_ID");
                String description = rs.getString("DESCRIPTION");
                Date dateStarted = rs.getDate("DATE_STARTED");
                Date dateFinished = rs.getDate("DATE_FINISHED");
                int balance = rs.getInt("BALANCE");
                String jobStatus = rs.getString("STATUS");
                
                Customer customer = CustomerBroker.getInstance().getById(customerId);
                List<Report> reportList = new ArrayList<>();
                List<User> userList = new ArrayList<>();
                List<Item> itemList = new ArrayList<>();
                
                job = new Job(jobName, houseNumber, street, city, province, country, postalCode, customer, description, dateStarted, dateFinished, balance, jobStatus, reportList, userList, itemList);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return job;
    }
     
//    public List<User> search(String keyword) {
//        String status = getConnection();
//        if (status == null || status.equals("connection error")) {
//            return null;
//        }
//        
//        List<User> userList = new ArrayList<>();
//        User user = null;
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(""
//                    + "SELECT u.user_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, GROUP_CONCAT(p.phone_number) as 'PHONE_NUMBER', u.password, u.firstname, u.lastname, r.role_name, u.email, u.hourly_rate, u.salt "
//                    + "     FROM `user` u "
//                    + "     JOIN `phone_user` up ON up.user_name = u.user_name "
//                    + "     JOIN `phone` p ON p.phone_id = up.phone_id "
//                    + "     JOIN `address` a ON u.address_id = a.address_id "
//                    + "     JOIN `role` r ON r.role_id = u.role_id "
//                    + "     WHERE u.user_name like ? "
//                    + "GROUP BY user_name;");
//            pstmt.setString(1, "%" + keyword +"%");
//            ResultSet rs = pstmt.executeQuery();
//       
//            while (rs.next()) {
//                String userName = rs.getString("USER_NAME");
//                int houseNumber = rs.getInt("HOUSE_NUMBER");
//                String street = rs.getString("STREET");
//                String city = rs.getString("CITY");
//                String province = rs.getString("PROVINCE");
//                String country = rs.getString("COUNTRY");
//                String postalCode = rs.getString("POSTAL_CODE");
//                
//                List<Long> phoneNumberList = new ArrayList<>();
//                String stringPhoneNumberList = rs.getString("PHONE_NUMBER");
//                String[] parts = stringPhoneNumberList.split(",");
//                for (String part : parts) {
//                    try {
//                        long phoneNumber = Long.parseLong(part);
//                        phoneNumberList.add(phoneNumber);
//                    } catch (NumberFormatException ex) {
//                    }
//                }
//                
//                String password = rs.getString("PASSWORD");
//                String firstname = rs.getString("FIRSTNAME");
//                String lastname = rs.getString("LASTNAME");
//                String role = rs.getString("ROLE_NAME");
//                String email = rs.getString("EMAIL");
//                int hourlyRate = rs.getInt("HOURLY_RATE");
//                String salt = rs.getString("SALT");
//                
//                user = new User(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstname, lastname, role, email, hourlyRate, salt);
//                userList.add(user);
//            }
//          
//        } catch (SQLException ex) {
//            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            pool.freeConnection(connection);
//        }
//        return userList;
//    }

    //returns "List of Users, empty List, null"
    public List<Job> getAll() {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }

        List<Job> jobList = new ArrayList<>();
        Job job = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT j.job_name, a.house_number, a.street, a.city, a.province, a.country, j.customer_id, j.description, j.date_started, j.date_finished, j.balance, j.status "
                    + "     FROM `job` j "
                    + "     JOIN `address` a ON u.address_id = a.address_id ");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String jobName = rs.getString("JOB_NAME");
                int houseNumber = rs.getInt("HOUSE_NUMBER");
                String street = rs.getString("STREET");
                String city = rs.getString("CITY");
                String province = rs.getString("PROVINCE");
                String country = rs.getString("COUNTRY");
                String postalCode = rs.getString("POSTAL_CODE");
                int customerId = rs.getInt("CUSTOMER_ID");
                String description = rs.getString("DESCRIPTION");
                Date dateStarted = rs.getDate("DATE_STARTED");
                Date dateFinished = rs.getDate("DATE_FINISHED");
                int balance = rs.getInt("BALANCE");
                String jobStatus = rs.getString("STATUS");
                
                Customer customer = CustomerBroker.getInstance().getById(customerId);
                List<Report> reportList = new ArrayList<>();
                List<User> userList = new ArrayList<>();
                List<Item> itemList = new ArrayList<>();
                
                job = new Job(jobName, houseNumber, street, city, province, country, postalCode, customer, description, dateStarted, dateFinished, balance, jobStatus, reportList, userList, itemList);
                jobList.add(job);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return jobList;
    }

    //returns "inserted, updated, error, exception"
    public String insert(Job job) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("select insert_job_func(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
             
            pstmt.setString(1, job.getJobName());
            pstmt.setInt(2, job.getHouseNumber());
            pstmt.setString(3, job.getStreet());
            pstmt.setString(4, job.getCity());
            pstmt.setString(5, job.getProvince());
            pstmt.setString(6, job.getCountry());
            pstmt.setString(7, job.getPostalCode());
            pstmt.setInt(8, job.getCustomer().getCustomerId());
            pstmt.setString(9, job.getDescription());
            pstmt.setDate(10, DataConverter.javaDateToSQL(job.getDateStarted()));
            pstmt.setDate(11, DataConverter.javaDateToSQL(job.getDateFinished()));
            pstmt.setInt(13, job.getBalance());
            pstmt.setString(14, job.getStatus());            

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
            
            //TODO ADD ARRAYLIST AS WELL as BRIDGING TABLES

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

    //returns "updated, error, exception"
    public String update(Job job) {        
        String status = insert(job);
        
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        if (status.contains("updated") || status.contains("inserted")) {
            return "updated";
        }
        
        return status; //expection, error
    }

    //returns "deleted, error, exception"
    public String delete(Job job) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
   
        try {
            PreparedStatement pstmt = connection.prepareStatement("select delete_job_func(?)");
            pstmt.setString(1, job.getJobName());

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
