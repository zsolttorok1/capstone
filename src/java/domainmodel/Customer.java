package domainmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {

    private String customerName;
    private Address address;
    List<Long> phoneNumberList;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String position;
    private String notes;

//    public Customer(String customerName, int houseNumber, String street, String city, String province, String country, String postalCode, List<Long> phoneNumberList, String firstName, String lastName, String companyName, String email, String position, String notes) {
//        this.customerName = customerName;
//        this.houseNumber = houseNumber;
//        this.street = street;
//        this.city = city;
//        this.province = province;
//        this.country = country;
//        this.postalCode = postalCode;
//        this.phoneNumberList = phoneNumberList;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.companyName = companyName;
//        this.email = email;
//        this.position = position;
//        this.notes = notes;
//    }
    
    public Customer() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getHouseNumber() {
        return address.getHouseNumber();
    }

    public void setHouseNumber(int houseNumber) {
        this.address.setHouseNumber(houseNumber);
    }

    public String getStreet() {
        return address.;
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
