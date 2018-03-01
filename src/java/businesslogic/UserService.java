package businesslogic;

import dataaccess.UserBroker;
import domainmodel.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    public String insert(String userName, String houseNumber, String street, String city, String province, String country, String postalCode, ArrayList<String> phoneNumberList, String password, String firstName, String lastName, String role, String email, String hourlyRate) {
        //check for nulls
        if (userName == null || houseNumber == null || street == null || city == null || province == null 
                || country == null || postalCode == null || phoneNumberList == null || password == null 
                || firstName == null || lastName == null || role == null || email == null || hourlyRate == null 
                || userName.isEmpty() || houseNumber.isEmpty() || street.isEmpty() || city.isEmpty() 
                || province.isEmpty() || country.isEmpty() || postalCode.isEmpty() || phoneNumberList.isEmpty() 
                || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || role.isEmpty() 
                || email.isEmpty() || hourlyRate.isEmpty()) {
            return "error";
        }

        //check for values being valid
        int intHouseNumber = 0;
        int intHourlyRate = 0;
        ArrayList<Integer> intPhoneNumberList = new ArrayList<>();
        
        //try catch these
        try {
            intHouseNumber = Integer.parseInt(houseNumber);
            intHourlyRate = Integer.parseInt(hourlyRate);
            for (String phoneNumber : phoneNumberList) {
                intPhoneNumberList.add(Integer.parseInt(phoneNumber));
            }
            
        } catch (NumberFormatException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }

        User user = new User(userName, intHouseNumber, street, city, province, country, postalCode, intPhoneNumberList, password, firstName, lastName, role, email, intHourlyRate);

        UserBroker userBroker = UserBroker.getInstance();
        
        return userBroker.insert(user);
    }

    public User viewUser(String userName) {
        UserBroker userBroker = UserBroker.getInstance();
        User user = userBroker.getByName(userName);

        return user;
    }

    public List<User> searchUser(String keyword) {
        UserBroker userBroker = UserBroker.getInstance();

        //this always return all items for now
        return userBroker.getAll();
    }

    public String update(String userName, String houseNumber, String street, String city, String province, String country, String postalCode, ArrayList<String> phoneNumberList, String password, String firstName, String lastName, String role, String email, String hourlyRate) {
        //check for nulls
        if (userName == null || houseNumber == null || street == null || city == null || province == null 
                || country == null || postalCode == null || phoneNumberList == null || password == null 
                || firstName == null || lastName == null || role == null || email == null || hourlyRate == null 
                || userName.isEmpty() || houseNumber.isEmpty() || street.isEmpty() || city.isEmpty() 
                || province.isEmpty() || country.isEmpty() || postalCode.isEmpty() || phoneNumberList.isEmpty() 
                || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || role.isEmpty() 
                || email.isEmpty() || hourlyRate.isEmpty()) {
            return "error";
        }
        
        UserBroker userBroker = UserBroker.getInstance();
        User user = userBroker.getByName(userName);
        if (user == null) {
            return "not found";
        }

        //check for values being valid
        int intHouseNumber = 0;
        int intHourlyRate = 0;
        ArrayList<Integer> intPhoneNumberList = new ArrayList<>();
        
        //try catch these
        try {
            intHouseNumber = Integer.parseInt(houseNumber);
            intHourlyRate = Integer.parseInt(hourlyRate);
            for (String phoneNumber : phoneNumberList) {
                intPhoneNumberList.add(Integer.parseInt(phoneNumber));
            }
            
        } catch (NumberFormatException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }

        user.setUsername(userName);
        user.setHouseNumber(intHouseNumber);
        user.setStreet(street);
        user.setCity(city);
        user.setProvince(province);
        user.setCountry(country);
        user.setPostalCode(postalCode);
        user.setPhoneNumberList(intPhoneNumberList);
        user.setPassword(password);
        user.setFirstname(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setEmail(email);
        user.setHourlyRate(intHourlyRate);

        return userBroker.update(user);
    }

    public String delete(String userName) {
        UserBroker userBroker = UserBroker.getInstance();
        User deletedUser = userBroker.getByName(userName);
        
        return userBroker.delete(deletedUser);
    }
}
