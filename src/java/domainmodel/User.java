package domainmodel;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    
    private String userName;
    private int houseNumber;
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    List<Long> phoneNumberList;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private int hourlyRate;
    //private int hours;

    public User(String userName, int houseNumber, String street, String city, String province, String country, String postalCode, List<Long> phoneNumberList, String password, String firstname, String lastname, String role, String email, int hourlyRate) {
        this.userName = userName;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumberList = phoneNumberList;
        this.password = password;
        this.firstName = firstname;
        this.lastName = lastname;
        this.role = role;
        this.email = email;
        this.hourlyRate = hourlyRate;
    }
    
    public User() {
        this.userName = "";
        this.houseNumber = 0;
        this.street = "";
        this.city = "";
        this.province = "";
        this.country = "";
        this.postalCode = "";
        this.phoneNumberList = new ArrayList<>();
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.role = "";
        this.email = "";
        this.hourlyRate = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public void setFirstname(String firstName) {
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
}
