package domainmodel;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {

    private String customerName;
    private String jobName;
    private ArrayList<Integer> phoneId;
    private int addressId;
    private String firstName;
    private String lastName;
    private String companyName;
    private String email;
    private String position;
    private String note;

    public Customer(String customerName, String jobName, ArrayList<Integer> phoneId, int addressId,
            String firstName, String lastName, String companyName, String email, String position, String note) {
        this.customerName = customerName;
        this.jobName = jobName;
        this.phoneId = phoneId;
        this.addressId = addressId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.email = email;
        this.position = position;
        this.note = note;
    }

    public Customer() {

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public ArrayList<Integer> getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(ArrayList<Integer> phoneId) {
        this.phoneId = phoneId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
