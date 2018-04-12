package domainmodel;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

/**
 * The java bean class that handles the User object attributes and getter/setter methods. 
 */
public class User implements Serializable {
    
    /**
     * The User's username, used to login and get identified by the system
     */
    private String userName;
    
    /**
     * The Address reference for the user
     */
    private Address address;
    
    /**
     * All the phone numbers that belong to the User
     */
    private List<Long> phoneNumberList;
    
    /**
     * The User's username, used to login and get identified by the system
     */
    private String password;
    
    /**
     * The First Name of the User
     */
    private String firstName;
    
    /**
     * The Last Name of the User
     */
    private String lastName;
    
    /**
     * The role of the user. Can be "owner", "manager" or "employee"
     */
    private String role;
    
    /**
     * The email address of the User
     */
    private String email;
    
    /**
     * The hourly rate to pay the user
     */
    private int hourlyRate;
    
    /**
     * The user's unique salt
     */
    private String salt;
    
    /**
     * Number of hours the user worked on the given job
     */
    private int hours;
    
    /**
     * Constructor
     * @param userName The User's username, used to login and get identified by the system
     * @param houseNumber The User's house number
     * @param street The User's street number
     * @param city The User's city
     * @param province The User's province
     * @param country The User's country
     * @param postalCode The User's postalCode
     * @param phoneNumberList The User's list of phone numbers
     * @param password The User's password
     * @param firstName The First Name of the User
     * @param lastName The Last Name of the User
     * @param role The role of the user. Can be "owner", "manager" or "employee"
     * @param email The email address of the User
     * @param hourlyRate The hourly rate to pay the user
     * @param salt The user's unique salt
     */
    public User(String userName, int houseNumber, String street, String city, String province, String country, String postalCode, List<Long> phoneNumberList, String password, String firstName, String lastName, String role, String email, int hourlyRate, String salt) {
        this.userName = userName;
        this.address = new Address();
        setHouseNumber(houseNumber);
        setStreet(street);
        setCity(city);
        setProvince(province);
        setCountry(country);
        setPostalCode(postalCode);
        this.phoneNumberList = phoneNumberList;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.hourlyRate = hourlyRate;
        this.salt = salt;
        this.hours = -1;
    }
    
    public User() {
        this.userName = "";
        this.address = new Address();
        this.phoneNumberList = new ArrayList<>();
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.role = "";
        this.email = "";
        this.hourlyRate = 0;
        this.salt = "";
        this.hours = -1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getHouseNumber() {
        return address.getHouseNumber();
    }

    public void setHouseNumber(int houseNumber) {
        this.address.setHouseNumber(houseNumber);
    }

    public String getStreet() {
        return address.getStreet();
    }

    public void setStreet(String street) {
        this.address.setStreet(street);;
    }

    public String getCity() {
        return address.getCity();
    }

    public void setCity(String city) {
        this.address.setCity(city);;
    }

    public String getProvince() {
        return address.getProvince();
    }

    public void setProvince(String province) {
        this.address.setProvince(province);
    }

    public String getCountry() {
        return this.address.getCountry();
    }

    public void setCountry(String country) {
        this.address.setCountry(country);
    }

    public String getPostalCode() {
        return address.getPostalCode();
    }

    public void setPostalCode(String postalCode) {
        this.address.setPostalCode(postalCode);
    }

    public List<Long> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<Long> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}