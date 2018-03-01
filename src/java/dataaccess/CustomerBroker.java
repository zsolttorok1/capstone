package dataaccess;

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

    public Customer getByName(String customerName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Customer customer = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM customer WHERE customer_name = ?");
            pstmt.setString(1, customerName);

            ResultSet rs = pstmt.executeQuery();

            ArrayList phoneIdList = new ArrayList();

            String customerName2 = null;
            String jobName = null;
            int phoneId = 0;
            int addressId = 0;
            String firstName = null;
            String lastName = null;
            String companyName = null;
            String email = null;
            String position = null;
            String note = null;

            while (rs.next()) {
                customerName2 = rs.getString("CUSTOMER_NAME");
                jobName = rs.getString("JOB_NAME");
                phoneId = rs.getInt("PHONE_ID");
                phoneIdList.add(phoneId);
                addressId = rs.getInt("ADDRESS_ID");
                firstName = rs.getString("FIRST_NAME");
                lastName = rs.getString("LAST_NAME");
                companyName = rs.getString("COMPANY_NAME");
                email = rs.getString("EMAIL");
                position = rs.getString("POSITION");
                note = rs.getString("NOTE");

            }
            customer = new Customer(customerName2, jobName, phoneIdList, addressId, firstName, lastName, companyName, email, position, note);
            pool.freeConnection(connection);

        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return customer;

    }

    public List<Customer> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        List<Customer> customerList = new ArrayList<>();
        Customer customer = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM customer");

            ResultSet rs = pstmt.executeQuery();

            ArrayList phoneIdList = new ArrayList();

            String customerName2 = null;
            String jobName = null;
            int phoneId = 0;
            int addressId = 0;
            String firstName = null;
            String lastName = null;
            String companyName = null;
            String email = null;
            String position = null;
            String note = null;

            while (rs.next()) {
                customerName2 = rs.getString("CUSTOMER_NAME");
                jobName = rs.getString("JOB_NAME");
                phoneId = rs.getInt("PHONE_ID");
                phoneIdList.add(phoneId);
                addressId = rs.getInt("ADDRESS_ID");
                firstName = rs.getString("FIRST_NAME");
                lastName = rs.getString("LAST_NAME");
                companyName = rs.getString("COMPANY_NAME");
                email = rs.getString("EMAIL");
                position = rs.getString("POSITION");
                note = rs.getString("NOTE");
            }
            customer = new Customer(customerName2, jobName, phoneIdList, addressId, firstName, lastName, companyName, email, position, note);
            customerList.add(customer);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return customerList;
    }

    public String insert(Customer customer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String result = null;

        try {
            ArrayList phoneIdList = new ArrayList();
            PreparedStatement pstmt = connection.prepareStatement("select insert_customer_func(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getJobName());

            for (int i = 0; i < phoneIdList.size(); i++) {
                pstmt.setInt(3, customer.getPhoneId().get(i));
            }

            pstmt.setInt(4, customer.getAddressId());
            pstmt.setString(5, customer.getFirstName());
            pstmt.setString(6, customer.getLastName());
            pstmt.setString(7, customer.getCompanyName());
            pstmt.setString(8, customer.getEmail());
            pstmt.setString(9, customer.getPosition());
            pstmt.setString(10, customer.getNote());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result = rs.getString(1);
            }
            pool.freeConnection(connection);

            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return result;
    }

    public String update(Customer customer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            ArrayList phoneIdList = new ArrayList();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE customer SET "
                    + "JOB_NAME = ?, PHONE_ID = ?, ADDRESS_ID = ?, FIRST_NAME = ?, LAST_NAME =?, "
                    + "COMPANY_NAME = ?, EMAIL = ?, POSITION = ?, NOTE =? WHERE CUSTOMER_NAME =?");

            pstmt.setString(1, customer.getJobName());

            for (int i = 0; i < phoneIdList.size(); i++) {
                pstmt.setInt(2, customer.getPhoneId().get(i));
            }

            pstmt.setInt(3, customer.getAddressId());
            pstmt.setString(4, customer.getFirstName());
            pstmt.setString(5, customer.getLastName());
            pstmt.setString(6, customer.getCompanyName());
            pstmt.setString(7, customer.getEmail());
            pstmt.setString(8, customer.getPosition());
            pstmt.setString(9, customer.getNote());
            pstmt.setString(10, customer.getCustomerName());
            
            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "updated";
    }

    public String delete(Customer customer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            ArrayList phoneIdList = new ArrayList();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM customer WHERE CUSTOMER_NAME = ?");
            pstmt.setString(1, customer.getCustomerName());

            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "deleted";
    }
}
