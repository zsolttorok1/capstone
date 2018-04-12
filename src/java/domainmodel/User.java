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

    /**
     * gets the useName
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets the userName
     * @param userName the userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * gets the houseNumber
     * @return the houseNumber
     */
    public int getHouseNumber() {
        return address.getHouseNumber();
    }

    /**
     * sets the houseNumber
     * @param houseNumber the houseNumber
     */
    public void setHouseNumber(int houseNumber) {
        this.address.setHouseNumber(houseNumber);
    }

    /**
     * gets the street
     * @return the street
     */
    public String getStreet() {
        return address.getStreet();
    }

    /**
     * sets the street 
     * @param street the street
     */
    public void setStreet(String street) {
        this.address.setStreet(street);;
    }

    /**
     * gets the city
     * @return the city
     */
    public String getCity() {
        return address.getCity();
    }

    /**
     * sets the city
     * @param city the city
     */
    public void setCity(String city) {
        this.address.setCity(city);;
    }

    /**
     * gets the province
     * @return the province
     */
    public String getProvince() {
        return address.getProvince();
    }

    /**
     * sets the province
     * @param province 
     */
    public void setProvince(String province) {
        this.address.setProvince(province);
    }

    /**
     * gets the country
     * @return the country
     */
    public String getCountry() {
        return this.address.getCountry();
    }

    /**
     * sets the country
     * @param country the country
     */
    public void setCountry(String country) {
        this.address.setCountry(country);
    }

    /**
     * gets the postalCode
     * @return the postalCode
     */
    public String getPostalCode() {
        return address.getPostalCode();
    }

    /**
     * sets the postalCode
     * @param postalCode the postalCode
     */
    public void setPostalCode(String postalCode) {
        this.address.setPostalCode(postalCode);
    }

    /**
     * gets the phoneNumberList, List of Longs
     * @return the phoneNumberList List, List of Longs
     */
    public List<Long> getPhoneNumberList() {
        return phoneNumberList;
    }

    /**
     * sets the phoneNumberList List, List of Longs
     * @param phoneNumberList the phoneNumberList List, List of Longs
     */
    public void setPhoneNumberList(List<Long> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    /**
     * gets the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the password 
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets the firstName
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the firstName
     * @param firstName the firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * gets the lastName
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets the lastName
     * @param lastName the lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * gets the role "employee", "manager" or "owner"
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * sets the role "employee", "manager" or "owner"
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * gets the email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the email
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets the hourlyRate
     * @return the hourlyRate in Integers
     */
    public int getHourlyRate() {
        return hourlyRate;
    }

    /**
     * sets the hourlyRate
     * @param hourlyRate the hourlyRate in Integers
     */
    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * gets the salt
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * sets the salt
     * @param salt the salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * gets the hours
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * sets the hours
     * @param hours the hours
     */
    public void setHours(int hours) {
        this.hours = hours;
    }
}