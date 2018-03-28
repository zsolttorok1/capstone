package businesslogic;

*** WORK IN PROGRESS ***

import dataaccess.UserBroker;
import domainmodel.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import utilities.HashingUtil;

public class JobService {

// ELECT j.job_name, a.house_number, a.street, a.city, a.province, a.country, j.customer_id, j.description, j.date_started, j.date_finished, j.balance, j.status "   
    
    
    public String insert(String jobName, String houseNumber, String street, String city, String province, String country, String postalCode, , String customerId, String dateStarted, String dateFinished, String balance, String status) {
    
        Job job = build(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstName, lastName, role, email, hourlyRate);
        
        String status = validate(user);
        
        if (status != null && status.equals("ok")) {
            user.setSalt(HashingUtil.generateSalt());   
            user.setPassword(HashingUtil.hashByKeccak512(user.getPassword(), user.getSalt()));
        
            UserBroker userBroker = UserBroker.getInstance();
            status = userBroker.insert(user);
        }

        return status; 
    }

    public User getByUserName(String userName) {
        
        if (userName != null && !userName.isEmpty()) {
            UserBroker userBroker = UserBroker.getInstance();
            return userBroker.getByName(userName);
        }
        else {
            return null;
        }
    }
    
    public User getByEmail(String email) {
        
        if (email != null && !email.isEmpty()) {
            UserBroker userBroker = UserBroker.getInstance();
            return userBroker.getByEmail(email);
        }
        else {
            return null;
        }
    }

    public List<User> searchUser(String keyword) {
        UserBroker userBroker = UserBroker.getInstance();
        List<User> userList = null;
        
        if (!keyword.isEmpty()) {
            userList = userBroker.search(keyword);
        }
        else {
            userList = userBroker.getAll();
        }
        
        if (userList == null)
            return null;
                
        return userList;
    }

    public String update(String userName, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String password, String firstName, String lastName, String role, String email, String hourlyRate) {
        User user = build(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstName, lastName, role, email, hourlyRate);
        
        return update(user);
    }
    
    public String update(User userNew) {
        UserBroker userBroker = UserBroker.getInstance();
        User user = null;
        String status = "";
        
        if (userNew.getUserName() != null) 
            user = userBroker.getByName(userNew.getUserName());
        else 
            return "invalid userName";
        
        if (user == null)
            return "username not found while attempting to update. Check database connection.";
        
        
        //prepare changed attributes on the updatable User
        if (userNew.getHouseNumber() > 0 )
            user.setHouseNumber(userNew.getHouseNumber());
        if (userNew.getStreet() != null && !userNew.getStreet().isEmpty() )
            user.setStreet(userNew.getStreet());
        if (userNew.getCity() != null && !userNew.getCity().isEmpty() )
            user.setCity(userNew.getCity());
        if (userNew.getProvince() != null && !userNew.getProvince().isEmpty() )
            user.setProvince(userNew.getProvince());
        if (userNew.getCountry() != null && !userNew.getCountry().isEmpty() )
            user.setCountry(userNew.getCountry());
        if (userNew.getPostalCode() != null && !userNew.getPostalCode().isEmpty() )
            user.setPostalCode(userNew.getPostalCode());
        if (userNew.getPhoneNumberList() != null && !userNew.getPhoneNumberList().isEmpty() )
            user.setPhoneNumberList(userNew.getPhoneNumberList());
        if (userNew.getPassword() != null && !userNew.getPassword().isEmpty() )
            user.setPassword(HashingUtil.hashByKeccak512(userNew.getPassword(), user.getSalt()));
        if (userNew.getFirstName() != null && !userNew.getFirstName().isEmpty() )
            user.setFirstName(userNew.getFirstName());
        if (userNew.getLastName() != null && !userNew.getLastName().isEmpty() )
            user.setLastName(userNew.getLastName());
        if (userNew.getRole() != null && !userNew.getRole().isEmpty() )
            user.setRole(userNew.getRole());
        if (userNew.getEmail() != null && !userNew.getEmail().isEmpty() )
            user.setEmail(userNew.getEmail());
        if (userNew.getHourlyRate() > 0 )
            user.setHourlyRate(userNew.getHourlyRate());
        
        //make sure that the new attribute values are valid, before updating.
        status = validate(user);
        if (status != null && status.equals("ok")) {
            return userBroker.update(user);
        }
        else {
            return status;
        }
    }
      
    private String validate(User user) {
        String status = "";
        
        if (user == null || user.getUserName().isEmpty()) {
            status += "invalid userName ";
        }
        if (user.getHouseNumber() <= 0) {
            status += "invalid houseNumber ";
        }
        if (user.getStreet() == null || user.getStreet().isEmpty()) {
            status += "invalid street ";
        }
        if (user.getCity() == null || user.getCity().isEmpty()) {
            status += "invalid city ";
        }
        if (user.getProvince() == null || user.getProvince().isEmpty()) {
            status += "invalid province ";
        }
        if (user.getCountry() == null || user.getCountry().isEmpty()) {
            status += "invalid country ";
        }
        if (user.getPostalCode() == null || user.getPostalCode().isEmpty()) {
            status += "invalid postalCode ";
        }
        if (user.getPhoneNumberList() == null || user.getPhoneNumberList().isEmpty()) {
            status += "invalid phoneNumberList ";
        }
        else {
            for (int i=0; i < user.getPhoneNumberList().size(); i++) {
                //regex this check for right number of digits, format, etc
                if (user.getPhoneNumberList().get(i) <= 0) {
                    status += "invalid phoneNumberFormat at phone entry#" + (i+1) + " ";
                }
            }
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            status += "invalid password ";
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            status += "invalid firstName ";
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            status += "invalid lastName ";
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            status += "invalid role ";
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            status += "invalid email ";
        }
        if (user.getHourlyRate() <= 0 ) {
            status += "invalid hourlyRate ";
        }
        
        if (status.isEmpty()) {
            return "ok";
        }
        else { 
            return status;
        }
    }
    
    private User build(String userName, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String password, String firstName, String lastName, String role, String email, String hourlyRate) {
        User user = new User();
        
        if (userName != null && !userName.isEmpty()) {
            user.setUserName(userName);
        }
        if (houseNumber != null && !houseNumber.isEmpty()) {
            try {
                int intHouseNumber = Integer.parseInt(houseNumber);
                user.setHouseNumber(intHouseNumber);
            } catch (NumberFormatException ex) {
                user.setHouseNumber(-1);
            }
        }
        if (street != null && !street.isEmpty()) {
            user.setStreet(street);
        }
        if (city != null && !city.isEmpty()) {
            user.setCity(city);
        }
        if (province != null && !province.isEmpty()) {
            user.setProvince(province);
        }
        if (country != null && !country.isEmpty()) {
            user.setCountry(country);
        }
        if (postalCode != null && !postalCode.isEmpty()) {
            user.setPostalCode(postalCode);
        }
        if (phoneNumberList != null && phoneNumberList.length != 0) {
            ArrayList<Long> intPhoneNumberList = new ArrayList<>();
            
            for (int i=0; i < phoneNumberList.length; i++) {
                try {
                    long phoneNumber = Long.parseLong(phoneNumberList[i]); 
                    intPhoneNumberList.add(phoneNumber);
                } catch (NumberFormatException ex) {
                    intPhoneNumberList.add(-1L);
                }
            }
            user.setPhoneNumberList(intPhoneNumberList);
        }
        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        }
        if (firstName != null && !firstName.isEmpty()) {
            user.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            user.setLastName(lastName);
        }
        if (role != null && !role.isEmpty()) {
            user.setRole(role);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        if (hourlyRate != null && !hourlyRate.isEmpty()) {
            try {
                int intHourlyRate = Integer.parseInt(hourlyRate);
                user.setHourlyRate(intHourlyRate);
            } catch (NumberFormatException ex) {
                user.setHourlyRate(-1);
            }
        }
        
        return user;
    }
    
    public String delete(String userName) {
        UserBroker userBroker = UserBroker.getInstance();
        User deletedUser = userBroker.getByName(userName);
        
        return userBroker.delete(deletedUser);
    }
    
    //returns "(role), invalid, null" 
    public String login(String username, String password) {
        String status = "";
        
        if (username != null && password != null && !username.equals("") && !password.equals("")) {
            UserBroker userBroker = UserBroker.getInstance();
            status = userBroker.login(username, password);
                       
            if (status == null)
                return "error while attempting to login. Check database connection.";
        }
        return status;
    }
    
    //returns "ok, invalid, null" 
    public String changePassword(String userName, String password) {
        String status = "";
        
        if (userName != null && password != null && !userName.equals("") && !password.equals("")) {
            UserBroker userBroker = UserBroker.getInstance();
            
            User user = userBroker.getByName(userName);
            
            user.setPassword(HashingUtil.hashByKeccak512(password, user.getSalt()));
            
            status = validate(user);
            if (status.equals("ok")) {
                status = userBroker.update(user);
            }
        }
        return status;
    }
    
    public int resetPassword(String email, String path, String url) {
        //generate new UUID
        String uuid = UUID.randomUUID().toString();
        String link = url + "?uuid=" + uuid;
        
        //find User by email
        UserService us = new UserService();
        User user = us.getByEmail(email);
        
        if (user != null) {
            
            //insert password change request
            ResetPasswordService rps = new ResetPasswordService();
            rps.insert(uuid, user.getUserName());

            try {
                HashMap<String, String> contents = new HashMap<>();
                contents.put("firstname", user.getFirstName());
                contents.put("lastname", user.getLastName());
                contents.put("username", user.getUserName()); 
                contents.put("link", link); 

                try {
                    WebMailService.sendMail(email, "Password Reset Request", path + "/emailtemplates/resetpassword.html", contents);
                } catch (IOException ex) {
                    Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (MessagingException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //OK, email sent
            return 1;
        }
        else {
            //email not found in Database
            return 2;
        }
    }
}
