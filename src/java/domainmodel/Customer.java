package domainmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The java bean class that handles the Customer object attributes and getter/setter methods. 
 * 
 */
public class Customer implements Serializable {

    private int customerId;
    private Address address;
    List<Long> phoneNumberList;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String position;
    private String notes;

    /**
     * Constructor
     * @param customerId the unique customer ID as a Int
     * @param houseNumber customer's houseNumber as a Int
     * @param street customer's streetname as a String
     * @param city customer's city as a String
     * @param province customer's province as a String
     * @param country customer's country as a String
     * @param postalCode customer's postal code as a String
     * @param phoneNumberList customer's phone number(s) as long(s)
     * @param firstName customer's first name as a String
     * @param lastName customer's last name as a String
     * @param companyName customer's company name as a String
     * @param email customer's email address as a String
     * @param position customer's position within their company as a string
     * @param notes employee notes on customer as a string 
     */
    public Customer(int customerId, int houseNumber, String street, String city, String province, String country, String postalCode, List<Long> phoneNumberList, String firstName, String lastName, String companyName, String email, String position, String notes) {
        this.customerId = customerId;
        this.address = new Address();
        setHouseNumber(houseNumber);
        setStreet(street);
        setCity(city);
        setProvince(province);
        setCountry(country);
        setPostalCode(postalCode);
        this.phoneNumberList = phoneNumberList;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.email = email;
        this.position = position;
        this.notes = notes;
    }
    
    /**
     * Basic Constructor
     */
    public Customer() {
        this.customerId = 0;
        this.address = new Address();
        this.phoneNumberList = new ArrayList<>();
        this.firstName = "";
        this.lastName = "";
        this.companyName = "";
        this.email = "";
        this.position = "";
        this.notes = "";
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    

}
