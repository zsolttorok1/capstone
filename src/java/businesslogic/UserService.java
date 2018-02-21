package businesslogic;

import dataaccess.UserBroker;
import domainmodel.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    public String addUser(String userName, String address, ArrayList<Integer> phone, String password, String firstName, String lastName, String role, String email, int hourlyRate, int hours) {
        //check for nulls
        if (userName == null || address == null || phone.isEmpty() || password == null || firstName == null || lastName == null
                || role == null || email == null || hourlyRate == 0 || hours == 0
                || userName.isEmpty() || address.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
                || role.isEmpty() || email.isEmpty()) {
            return "error";
        }

        //check for values being valid
        int intAddress = 0;
        //try catch this
        try {
            intAddress = Integer.parseInt(address);

        } catch (NumberFormatException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }

        User user = new User(userName, intAddress, phone, password, firstName, lastName, role, email, hourlyRate, hours);

        UserBroker userBroker = new UserBroker();
        return userBroker.insert(user);
    }

    public User viewUser(String userName) {
        UserBroker userBroker = new UserBroker();

        User user = userBroker.getByName(userName);

        return user;
    }

    public List<User> searchUser(String keyword) {
        UserBroker userBroker = new UserBroker();

        //this always return all items for now
        return userBroker.getAll();
    }

    public String edit(String userName, String address, ArrayList<Integer> phone, String password, String firstName, String lastName, String role, String email, int hourlyRate, int hours) {
        UserBroker userBroker = new UserBroker();
        User user = userBroker.getByName(userName);

        int intAddress = 0;
        //try catch this
        try {
            intAddress = Integer.parseInt(address);

        } catch (NumberFormatException ex) {
            Logger.getLogger(UserBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }

        user.setAddress(intAddress);
        user.setPhone(phone);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setEmail(email);
        user.setHourlyRate(hourlyRate);
        user.setHours(hours);

        return userBroker.update(user);
    }

    public String delete(String userName) {
        UserBroker userBroker = new UserBroker();
        User deletedUser = userBroker.getByName(userName);
        return userBroker.delete(deletedUser);
    }

    private int parseInteger(int addressId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
