package businesslogic;

import dataaccess.UserBroker;
import domainmodel.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import utilities.HashingUtil;

/**
 * User Service handles all the operations regarding the User Object, including inserting, deleting, updating
 * and building.
 * 
 */
public class UserService {

    /**
     * Insert calls the UserBroker class in order to insert a User Object into the database.
     *
     * @param userName unique username as a String
     * @param houseNumber house number of user as a String
     * @param street street name of user as a String
     * @param city city of user as a String
     * @param province province of user as a String
     * @param country country of user as a String
     * @param postalCode postal code of user as a String
     * @param phoneNumberList list of phone number(s) of user as String(s)
     * @param password password of user  of user as a String
     * @param firstName first name of user as a String
     * @param lastName last name of user as a String
     * @param role role of user as a String
     * @param email email address of user as a String
     * @param hourlyRate hourly rate of user as a String
     * @return status of insertion into database
     */
    public String insert(String userName, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String password, String firstName, String lastName, String role, String email, String hourlyRate) {
    
        User user = build(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstName, lastName, role, email, hourlyRate);
        
        String status = validate(user);
        
        if (status != null && status.equals("ok")) {
            user.setSalt(HashingUtil.generateSalt());
            user.setPassword(HashingUtil.hashByKeccak512(user.getPassword(), user.getSalt()));
        
            UserBroker userBroker = UserBroker.getInstance();
            status = userBroker.insert(user);
          
        }
        
        if (status != null && status.contains("inserted")) {
            status = "Employee Successfully Added";
        }
        else if (status != null && status.contains("updated")) {
            status = "Employee Successfully Updated";
        }

        return status; 
    }

    /**
     * Retrieves User Object (if found) by its unique user name from the Broker.
     *
     * @param userName unique username to be matched to User Object
     * @return matching user object
     */
    public User getByUserName(String userName) {
        
        if (userName != null && !userName.isEmpty()) {
            UserBroker userBroker = UserBroker.getInstance();
            return userBroker.getByName(userName);
        }
        else {
            return null;
        }
    }
    
    /**
     * Retrieves User Object (if found) by its unique email address from the Broker.
     *
     * @param email email address to be matched to User Object
     * @return matching user object
     */
    public User getByEmail(String email) {
        
        if (email != null && !email.isEmpty()) {
            UserBroker userBroker = UserBroker.getInstance();
            return userBroker.getByEmail(email);
        }
        else {
            return null;
        }
    }
    
    /**
     * Retrieves all User Objects currently in Database using UserBroker.
     *
     * @return list of all User Objects
     */
    public List<User> getAll() {
        UserBroker userBroker = UserBroker.getInstance();
        return userBroker.getAll();
    }

    /**
     * Searches through all existing users for matching keyword using UserBroker.
     *
     * @param keyword search term entered
     * @return list of user Objects which match keyword
     */
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

    /**
     * Updates existing User Object by setting its attributes to new parameters
     *
     * @param userName unique username as a String
     * @param houseNumber house number of user as a String
     * @param street street name of user as a String
     * @param city city of user as a String
     * @param province province of user as a String
     * @param country country of user as a String
     * @param postalCode postal code of user as a String
     * @param phoneNumberList list of phone number(s) of user as String(s)
     * @param password password of user  of user as a String
     * @param firstName first name of user as a String
     * @param lastName last name of user as a String
     * @param role role of user as a String
     * @param email email address of user as a String
     * @param hourlyRate hourly rate of user as a String
     * @return updated user object
     */
    public String update(String userName, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String password, String firstName, String lastName, String role, String email, String hourlyRate) {
        User user = build(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstName, lastName, role, email, hourlyRate);
        
        return update(user);
    }
    
    /**
     * Updates existing User Object to match temporary new User Object
     *
     * @param userNew temporary new user object used to update user object matching its name
     * @return status of update from database
     */
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
            status = userBroker.update(user);
                    
            if (status != null && status.contains("inserted")) {
                status = "Employee Successfully Added";
            }
            else if (status != null && status.contains("updated")) {
                status = "Employee Successfully Updated";
            }
        }
        
        return status;
    }
      
    /**
     * Makes sure none of the parameters in User object are null.
     *
     * @param user job object to be examined
     * @return status of validation check
     */
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
    
    /**
     * Builds USer object using parameters and validates data.
     *
     * @param userName unique username as a String
     * @param houseNumber house number of user as a String
     * @param street street name of user as a String
     * @param city city of user as a String
     * @param province province of user as a String
     * @param country country of user as a String
     * @param postalCode postal code of user as a String
     * @param phoneNumberList list of phone number(s) of user as String(s)
     * @param password password of user  of user as a String
     * @param firstName first name of user as a String
     * @param lastName last name of user as a String
     * @param role role of user as a String
     * @param email email address of user as a String
     * @param hourlyRate hourly rate of user as a String
     * @return built user object
     */
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
    
    /**
     * Deletes an existing User object from database based on its unique user name.
     *
     * @param userName user name of user to be deleted as a string
     * @return status of the deletion
     */
    public String delete(String userName) {
        UserBroker userBroker = UserBroker.getInstance();
        User deletedUser = userBroker.getByName(userName);
        
        return userBroker.delete(deletedUser);
    }
    
    /**
     * Log in method to access employee (user) website.
     *
     * @param username username of User as a String
     * @param password password of User as a String
     * @return status of login progress. "role, invalid, null"
     */
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
    
    /**
     * Changes password of User and saves it securely using hashing + salt
     *
     * @param userName username of User as a String
     * @param password new password to be set for User as a String
     * @return status of password changing progress. "ok, invalid, null" 
     */
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
    
    /**
     * Generates unique password reset link for user to receive when resetting their password.
     *
     * @param email email address to send link to as a String
     * @param path the reference to the project's relative path
     * @param url url of this application
     * @return the status number of the unique link generating
     */
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
