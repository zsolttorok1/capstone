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
import utilities.HashingUtil;

/**
 * Broker class which communicates with JDBC and handles all the Customer information management.
 */
public class UserBroker {
    /**
     * contains the CustomerBroker instance
     */
    private static UserBroker userBroker;
    
    /**
     * Reference to the ConnectionPool
     */
    private ConnectionPool pool;
    
    /**
     * Reference to the Connection
     */
    private Connection connection;
    
    /**
     * Singleton method which gets the UserBroker instance.
     * @return the UserBroker instance
     */
    public static UserBroker getInstance() {
        if (userBroker == null) {
            userBroker = new UserBroker();
        }
        
        return userBroker;
    }
    
    /**
     * Default constructor
     */
    private UserBroker() {
    }
    
    /**
     * Gets the next free connection from the connection pool.
     * @return "ok" if everything went fine, "connection error" if there was an issue in the process.
     */
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

    /**
     * Gets the User from the userName
     * @param userName the userName to search for
     * @return the found User, or null if not found
     */
    public User getByName(String userName) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT u.user_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, GROUP_CONCAT(p.phone_number) as 'PHONE_NUMBER', u.password, u.firstname, u.lastname, r.role_name, u.email, u.hourly_rate, u.salt "
                    + "     FROM `user` u "
                    + "     JOIN `phone_user` up ON up.user_name = u.user_name "
                    + "     JOIN `phone` p ON p.phone_id = up.phone_id "
                    + "     JOIN `address` a ON u.address_id = a.address_id "
                    + "     JOIN `role` r ON r.role_id = u.role_id "
                    + "     WHERE u.user_name = ?;");
            pstmt.setString(1, userName);
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
                
                String password = rs.getString("PASSWORD");
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                String role = rs.getString("ROLE_NAME");
                String email = rs.getString("EMAIL");
                int hourlyRate = rs.getInt("HOURLY_RATE");
                String salt = rs.getString("SALT");
                
                user = new User(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstname, lastname, role, email, hourlyRate, salt);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return user;
    }
    
    /**
     * Gets the User from the email address
     * @param email the email address to search for
     * @return the found User, or null if not found
     */
    public User getByEmail(String email) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT u.user_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, GROUP_CONCAT(p.phone_number) as 'PHONE_NUMBER', u.password, u.firstname, u.lastname, r.role_name, u.email, u.hourly_rate, u.salt "
                    + "     FROM `user` u "
                    + "     JOIN `phone_user` up ON up.user_name = u.user_name "
                    + "     JOIN `phone` p ON p.phone_id = up.phone_id "
                    + "     JOIN `address` a ON u.address_id = a.address_id "
                    + "     JOIN `role` r ON r.role_id = u.role_id "
                    + "     WHERE u.email = ?;");
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
       
            // have the "&& house number > 0" check here to make sure you dont go in the empty user 
            if (rs.next() && rs.getInt("HOUSE_NUMBER") > 0) {
                String userName = rs.getString("USER_NAME");
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
                
                String password = rs.getString("PASSWORD");
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                String role = rs.getString("ROLE_NAME");
                int hourlyRate = rs.getInt("HOURLY_RATE");
                String salt = rs.getString("SALT");
                
                user = new User(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstname, lastname, role, email, hourlyRate, salt);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return user;
    }
 
    /**
     * Searches for any keyword hits over the User table at the database.
     * @param keyword the keyword to search for
     * @return the List of found User object hits
     */
    public List<User> search(String keyword) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        List<User> userList = new ArrayList<>();
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT u.user_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, GROUP_CONCAT(p.phone_number) as 'PHONE_NUMBER', u.password, u.firstname, u.lastname, r.role_name, u.email, u.hourly_rate, u.salt "
                    + "     FROM `user` u "
                    + "     JOIN `phone_user` up ON up.user_name = u.user_name "
                    + "     JOIN `phone` p ON p.phone_id = up.phone_id "
                    + "     JOIN `address` a ON u.address_id = a.address_id "
                    + "     JOIN `role` r ON r.role_id = u.role_id "
                    + "     WHERE u.user_name like ? "
                    + "GROUP BY user_name;");
            pstmt.setString(1, "%" + keyword +"%");
            ResultSet rs = pstmt.executeQuery();
       
            while (rs.next()) {
                String userName = rs.getString("USER_NAME");
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
                
                String password = rs.getString("PASSWORD");
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                String role = rs.getString("ROLE_NAME");
                String email = rs.getString("EMAIL");
                int hourlyRate = rs.getInt("HOURLY_RATE");
                String salt = rs.getString("SALT");
                
                user = new User(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstname, lastname, role, email, hourlyRate, salt);
                userList.add(user);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return userList;
    }

    /**
     * Gets all the Users from the database
     * @return a List of the received User objects, or null if there is an issue
     */
    public List<User> getAll() {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }

        List<User> userList = new ArrayList<>();
        User user = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT u.user_name, a.house_number, a.street, a.city, a.province, a.country, a.postal_code, GROUP_CONCAT(p.phone_number) as 'PHONE_NUMBER', u.password, u.firstname, u.lastname, r.role_name, u.email, u.hourly_rate, u.salt "
                    + "    FROM `user` u "
                    + "    JOIN `phone_user` up ON up.user_name = u.user_name "
                    + "    JOIN `phone` p ON p.phone_id = up.phone_id "
                    + "    JOIN `address` a ON u.address_id = a.address_id "
                    + "    JOIN `role` r ON r.role_id = u.role_id "
                    + "GROUP BY user_name;");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next() && rs.getInt("HOUSE_NUMBER") > 0) {
                String userName = rs.getString("USER_NAME");
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

                String password = rs.getString("PASSWORD");
                String firstName = rs.getString("FIRSTNAME");
                String lastName = rs.getString("LASTNAME");
                String role = rs.getString("ROLE_NAME");
                String email = rs.getString("EMAIL");
                int hourlyRate = rs.getInt("HOURLY_RATE");
                String salt = rs.getString("SALT");
                //hourly = rs.getInt("HOURLY");
                
                user = new User(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstName, lastName, role, email, hourlyRate, salt);
                userList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return userList;
    }

    /**
     * Inserts the User object information to the database
     * @param user the User object
     * @return "inserted" if everything went ok, "error" if there was an expected issue, "exception" if there was an unexpected issue.
     */
    public String insert(User user) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("select insert_user_func(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, user.getUserName());
            pstmt.setInt(2, user.getHouseNumber());
            pstmt.setString(3, user.getStreet());
            pstmt.setString(4, user.getCity());
            pstmt.setString(5, user.getProvince());
            pstmt.setString(6, user.getCountry());
            pstmt.setString(7, user.getPostalCode());
            pstmt.setString(8, user.getPassword());
            pstmt.setString(9, user.getFirstName());
            pstmt.setString(10, user.getLastName());
            pstmt.setString(11, user.getRole());
            pstmt.setString(12, user.getEmail());
            pstmt.setInt(13, user.getHourlyRate());
            pstmt.setString(14, user.getSalt());            
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

            //inserting phone numbers
            String stringPhoneNumberList = "";
            String status2 = "";
            for (long phoneNumber : user.getPhoneNumberList()) {
                stringPhoneNumberList += phoneNumber + ",";
            }
            pstmt = connection.prepareStatement("select insert_phoneList_user_func(?, ?)");
            pstmt.setString(1, user.getUserName());
            pstmt.setString(2, stringPhoneNumberList);
            
            rs = pstmt.executeQuery();
            //get the status report from current database function
            while (rs.next()) {
                status2 = rs.getString(1);
            }
            //if something unexpected happened, rollback any changes.
            if (status2 == null || status2.contains("error")) {
                connection.rollback();
                return status2;
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

    /**
     * Updates the User object information at the database
     * @param user the User object
     * @return "updated" if everything went ok, "error" if there was an expected issue, "exception" if there was an unexpected issue.
     */
    public String update(User user) {        
        String status = insert(user);
        
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
                
        return status; //expection, error
    }

    /**
     * Deletes the User object information from the database
     * @param user the User object
     * @return "deleted" if everything went ok, "error" if there was an expected issue, "exception" if there was an unexpected issue.
     */
    public String delete(User user) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return "Database connection error.";
        }
   
        try {
            PreparedStatement pstmt = connection.prepareStatement("select delete_user_func(?)");
            pstmt.setString(1, user.getUserName());

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
     * Logs in the user.
     * @param username the User's username
     * @param password the User's password
     * @return "valid" if the credentials are ok, "invalid" if credentials are not ok, "null" if any other unexpected issue occurred.
     */
    public String login(String username, String password) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT u.user_name, u.password, r.role_name, u.salt "
                    + "FROM `user` u "
                    + "JOIN `role` r ON r.role_id = u.role_id " 
                    + "WHERE u.user_name = ?;");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
   
            if (rs.next()) {
                String databasePassword = rs.getString("PASSWORD");
                String role = rs.getString("ROLE_NAME");
                String salt = rs.getString("SALT");
            
                password = HashingUtil.hashByKeccak512(password,salt);
                
                if (databasePassword.equals(password)) {
                    return role;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return "invalid";
    }
}
