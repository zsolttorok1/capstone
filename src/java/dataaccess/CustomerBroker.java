/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import businesslogic.CustomerService;
import database.ConnectionPool;
import domainmodel.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerBroker {
    private static CustomerBroker customerBroker;
    private ConnectionPool pool;
    private Connection connection;
    
    public static CustomerBroker getInstance() {
        if (customerBroker == null) {
            customerBroker = new CustomerBroker();
        }
        
        return customerBroker;
    }
    
    private CustomerBroker() {
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
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "connection error";
        }
    }

    //returns "Customer, null"
    public Customer getById(int customerId) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        Customer customer = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                + "SELECT c.customer_id, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, GROUP_CONCAT(p.phone_number) as 'PHONE_NUMBER', c.firstname, c.lastname, c.company_name, c.email, po.position_name, c.notes "
                + "FROM `customer` c "
                + "JOIN `phone_customer` pc ON pc.customer_id = c.customer_id "
                + "JOIN `phone` p ON p.phone_id = pc.phone_id "
                + "JOIN `address` a ON a.address_id = c.address_id "
                + "JOIN `position` po ON po.position_id = c.position_id "
                + "WHERE c.customer_id = ?; ");
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
       
            if (rs.next() && rs.getInt("HOUSE_NUMBER") > 0) {
                int houseNumber = rs.getInt("HOUSE_NUMBER");
                String street = rs.getString("STREET");
                String city = rs.getString("CITY");
                String province = rs.getString("PROVINCE");
                String country = rs.getString("COUNTRY");
                String postalCode = rs.getString("POSTAL_CODE");
                
                List<Long> phoneNumberList = new ArrayList<>();
                String stringPhoneNumberList = rs.getString("PHONE_NUMBER");
                String[] parts = stringPhoneNumberList.split(",");
                for (String part : parts) {
                    try {
                        long phoneNumber = Long.parseLong(part);
                        phoneNumberList.add(phoneNumber);
                    } catch (NumberFormatException ex) {
                    }
                }
                
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                String companyName = rs.getString("COMPANY_NAME");
                String email = rs.getString("EMAIL");
                String positionName = rs.getString("POSITION_NAME");
                String notes = rs.getString("NOTES");
                
                customer = new Customer(customerId, houseNumber, street, city, province, country, postalCode, phoneNumberList, firstname, lastname, companyName, email, positionName, notes);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return customer;
    }

    //returns "List of Customers, empty List, null"
    public List<Customer> getAll() {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        Customer customer = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                + "SELECT c.customer_id, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, GROUP_CONCAT(p.phone_number) as 'PHONE_NUMBER', c.firstname, c.lastname, c.company_name, c.email, po.position_name, c.notes "
                + "FROM `customer` c "
                + "JOIN `phone_customer` pc ON pc.customer_id = c.customer_id "
                + "JOIN `phone` p ON p.phone_id = pc.phone_id "
                + "JOIN `address` a ON a.address_id = c.address_id "
                + "JOIN `position` po ON po.position_id = c.position_id "
                + "GROUP BY customer_id;");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next() && rs.getInt("HOUSE_NUMBER") > 0) {
                int customerId = rs.getInt("CUSTOMER_ID");
                int houseNumber = rs.getInt("HOUSE_NUMBER");
                String street = rs.getString("STREET");
                String city = rs.getString("CITY");
                String province = rs.getString("PROVINCE");
                String country = rs.getString("COUNTRY");
                String postalCode = rs.getString("POSTAL_CODE");
                
                List<Long> phoneNumberList = new ArrayList<>();
                String stringPhoneNumberList = rs.getString("PHONE_NUMBER");
                String[] parts = stringPhoneNumberList.split(",");
                for (String part : parts) {
                    try {
                        long phoneNumber = Long.parseLong(part);
                        phoneNumberList.add(phoneNumber);
                    } catch (NumberFormatException ex) {
                    }
                }
                
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                String companyName = rs.getString("COMPANY_NAME");
                String email = rs.getString("EMAIL");
                String positionName = rs.getString("POSITION_NAME");
                String notes = rs.getString("NOTES");
                
                
                customer = new Customer(customerId, houseNumber, street, city, province, country, postalCode, phoneNumberList, firstname, lastname, companyName, email, positionName, notes);
                customerList.add(customer);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return customerList;
    }

    //returns "inserted, updated, error, exception"
    public String insert(Customer customer) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("select insert_customer_func(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, customer.getCustomerId());
            pstmt.setInt(2, customer.getHouseNumber());
            pstmt.setString(3, customer.getStreet());
            pstmt.setString(4, customer.getCity());
            pstmt.setString(5, customer.getProvince());
            pstmt.setString(6, customer.getCountry());
            pstmt.setString(7, customer.getPostalCode());
            pstmt.setString(8, customer.getFirstName());
            pstmt.setString(9, customer.getLastName());
            pstmt.setString(10, customer.getCompanyName());
            pstmt.setString(11, customer.getEmail());
            pstmt.setString(12, customer.getPosition());
            pstmt.setString(13, customer.getNotes());

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
            
            //database is sending back the newly generated ID together with "inserted" string.
            String customerId =  status.replace("inserted", "");
            if (customerId != null && !customerId.isEmpty()) {
                try {
                    int intCustomerId = Integer.parseInt(customerId);
                    customer.setCustomerId(intCustomerId);
                } catch (NumberFormatException ex) {
                    customer.setCustomerId(-1);
                }
            }
          
            //inserting phone numbers
            String stringPhoneNumberList = "";
            for (long phoneNumber : customer.getPhoneNumberList()) {
                stringPhoneNumberList += phoneNumber + ",";
            }
            pstmt = connection.prepareStatement("select insert_phoneList_customer_func(?, ?)");
            pstmt.setInt(1, customer.getCustomerId());
            pstmt.setString(2, stringPhoneNumberList);
            
            rs = pstmt.executeQuery();
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
        
        if (status != null && status.contains("inserted")) {
            return "Customer successfully inserted";
        }
        else {
            return status;
        }
    }

    //returns "updated, error, exception"
    public String update(Customer customer) {
        String status = insert(customer);
        
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        if (status.contains("updated") || status.contains("inserted")) {
            return "updated";
        }
        
        return status; //expection, error
    }

    //returns "deleted, error, exception"
    public String delete(Customer customer) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
   
        try {
            PreparedStatement pstmt = connection.prepareStatement("select delete_customer_func(?)");
            pstmt.setInt(1, customer.getCustomerId());

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
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex1);
                return "exception";
            }
            return "exception";
        } finally {
            pool.freeConnection(connection);
        }

        return status;
    }
    
    public List<Customer> search(String keyword) {
        getConnection();
//        String status = getConnection();
//        if (status == null || status.equals("connection error")) {
//            return null;
//        }
        
        List<Customer> customerList = new ArrayList<>();
        Customer customer = null;
        CustomerService cs = new CustomerService();
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT i.customer_id"
                    + "     FROM customer i" 
                    + "     WHERE i.firstname like ? OR i.lastname like ?;");
                  
            pstmt.setString(1, "%" + keyword +"%");
            pstmt.setString(2, "%" + keyword +"%");
            ResultSet rs = pstmt.executeQuery();
       
            while (rs.next()) {
                String customerId = rs.getString("CUSTOMER_ID");

                customer = cs.getByCustomerId(customerId);
                customerList.add(customer);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return customerList;
    }
}
