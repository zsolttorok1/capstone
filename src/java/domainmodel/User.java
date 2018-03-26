package domainmodel;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    
    private String userName;
    private Address address;
    List<Long> phoneNumberList;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private int hourlyRate;
    //private int hours;

    public User(String userName, int houseNumber, String street, String city, String province, String country, String postalCode, List<Long> phoneNumberList, String password, String firstName, String lastName, String role, String email, int hourlyRate) {
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
}