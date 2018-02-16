package businesslogic;

import dataaccess.UserBroker;
import domainmodel.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public String addUser(String userName, int address, ArrayList<Integer> phone, String password, String firstName, String lastName,String role, String email, int hourlyRate, int hours) {
    //check for nulls
    if(userName == null || address == 0 || phone.isEmpty() || password == null || firstName == null || lastName == null ||
             role == null || email == null || hourlyRate == 0 || hours == 0 ||
            userName.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() ||
             role.isEmpty() || email.isEmpty()){
        return "error";
    }
    
    //check for values being valid
    
    User user = new User(userName, address, phone, password, firstName, lastName, role, email, hourlyRate, hours);
    
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
        
        return userBroker.getAll();
    }
    
    public boolean edit(String userName, int address, ArrayList<Integer> phone, String password, String firstName, String lastName,String role, String email, int hourlyRate, int hours){
        UserBroker userBroker = new UserBroker();
        User user = userBroker.getByName(userName);
        
        user.setAddress(address);
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
   public boolean delete(String userName){
       UserBroker userBroker = new UserBroker();
       User deletedUser = userBroker.getByName(userName);
       return userBroker.delete(deletedUser);
   }

    private int parseInteger(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
