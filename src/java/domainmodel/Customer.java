package domainmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * gets the customerId
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * sets the customerId
     * @param customerId the customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * gets the houseNumber in integers
     * @return the houseNumber
     */
    public int getHouseNumber() {
        return address.getHouseNumber();
    }

    /**
     * sets the houseNumber in integers
     * @param houseNumber 
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
     * get the country
     * @return the country
     */
    public String getCountry() {
        return this.address.getCountry();
    }

    /**
     * set the country
     * @param country the country
     */
    public void setCountry(String country) {
        this.address.setCountry(country);
    }

    /**
     * get the postalCode
     * @return the postalCode
     */
    public String getPostalCode() {
        return address.getPostalCode();
    }

    /**
     * set the postalCode
     * @param postalCode the postalCode
     */
    public void setPostalCode(String postalCode) {
        this.address.setPostalCode(postalCode);
    }

    /**
     * get the phoneNumberList in List of Longs
     * @return the phoneNumberList in List of Longs
     */
    public List<Long> getPhoneNumberList() {
        return phoneNumberList;
    }

    /**
     * sets the phoneNumberList in List of Longs
     * @param phoneNumberList the phoneNumberList in List of Longs
     */
    public void setPhoneNumberList(List<Long> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    /**
     * gets the fistName
     * @return the fistName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the fistName
     * @param firstName the fistName
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
     * gets the companyName
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * sets the companyName
     * @param companyName the companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
     * gets the position
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * sets the position
     * @param position the position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * gets the notes
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * sets the notes
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
