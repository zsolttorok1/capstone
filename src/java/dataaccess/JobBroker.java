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
                    + "SELECT j.job_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, j.customer_id, j.description, j.date_started, j.date_finished, j.balance, j.status "
                    + "     FROM `job` j "
                    + "     JOIN `address` a ON j.address_id = a.address_id "
                    + "     WHERE j.job_name = ?;");
            pstmt.setString(1, jobName);
            ResultSet rs = pstmt.executeQuery();
       
            if (rs.next() && rs.getInt("HOUSE_NUMBER") > 0) {
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
            
            if (job != null) {
                //attach userList
                List<User> userList = new ArrayList<>();
                pstmt = connection.prepareStatement(""
                        + "SELECT user_name, hours "
                        + "     FROM `job_user`"
                        + "     WHERE job_name = ?;");
                pstmt.setString(1, jobName);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String userName = rs.getString("USER_NAME");
                    int hours = rs.getInt("HOURS");

                    User user = UserBroker.getInstance().getByName(userName);
                    user.setHours(hours);
                    userList.add(user);
                }
                job.setUserList(userList);

                //attach itemList
                List<Item> itemList = new ArrayList<>();
                pstmt = connection.prepareStatement(""
                        + "SELECT item_name, quantity, note "
                        + "     FROM `job_item` "
                        + "     WHERE job_name = ?;");
                pstmt.setString(1, jobName);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String itemName = rs.getString("ITEM_NAME");
                    int quantity = rs.getInt("QUANTITY");
                    String note = rs.getString("NOTE");

                    Item item = ItemBroker.getInstance().getByName(itemName);
                    //save original inventory quantity 
                    int inventoryQuantity = item.getQuantity();
                    
                    item.setQuantity(quantity);
                    item.setNote(note);
                    item.setInventoryQuantity(inventoryQuantity);
                    itemList.add(item);
                }
                job.setItemList(itemList);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(JobBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return job;
    }
     
    public List<Job> search(String keyword) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        List<Job> jobList = new ArrayList<>();
        Job job = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT j.job_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, j.customer_id, j.description, j.date_started, j.date_finished, j.balance, j.status "
                    + "     FROM `job` j "
                    + "     JOIN `address` a ON j.address_id = a.address_id "
                    + "     WHERE j.job_name like ?;");
            pstmt.setString(1, "%" + keyword +"%");
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
                    + "SELECT j.job_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, j.customer_id, j.description, j.date_started, j.date_finished, j.balance, j.status "
                    + "     FROM `job` j "
                    + "     JOIN `address` a ON j.address_id = a.address_id ");

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
            PreparedStatement pstmt = connection.prepareStatement("select insert_job_func(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
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
            pstmt.setInt(12, job.getBalance());
            pstmt.setString(13, job.getStatus());   

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
    
    //returns "assigned, error, exception"
    public String assignUser(String jobName, String userName, int hours) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("select assign_user_func(?, ?, ?)");
            
            pstmt.setString(1, jobName);
            pstmt.setString(2, userName);
            pstmt.setInt(3, hours);
            
            ResultSet rs = pstmt.executeQuery();
            //get the status report from current database function
            if (rs.next()) {
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
    
    //returns "allocated, error, exception"
    public String allocateItem(String jobName, String itemName, int quantity, String note) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("select allocate_item_func(?, ?, ?, ?)");
            
            pstmt.setString(1, jobName);
            pstmt.setString(2, itemName);
            if (note != null && !note.isEmpty()) {
                pstmt.setString(3, note);
            }
            else {
                pstmt.setString(3, "");
            }
            pstmt.setInt(4, quantity);
            
            ResultSet rs = pstmt.executeQuery();
            //get the status report from current database function
            if (rs.next()) {
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
    
    //returns "unallocated, error, exception"
    public String unallocateItem(String jobName, String itemName) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("select unallocate_item_func(?, ?)");
            
            pstmt.setString(1, jobName);
            pstmt.setString(2, itemName);
            
            ResultSet rs = pstmt.executeQuery();
            //get the status report from current database function
            if (rs.next()) {
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
    
    //returns "unassigned, error, exception"
    public String unassignUser(String jobName, String userName) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("select unassign_user_func(?, ?)");
            
            pstmt.setString(1, jobName);
            pstmt.setString(2, userName);
            
            ResultSet rs = pstmt.executeQuery();
            //get the status report from current database function
            if (rs.next()) {
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
}
