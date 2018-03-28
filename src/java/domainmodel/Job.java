package domainmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Job implements Serializable{
    
    private String jobName;
    private Address address;
    private Customer customer;
    private String description; 
    private Date dateStarted;
    private Date dateFinished;
    private int balance; 
    private String status; 
    
    private List<Report> reportList;
    private List<User> userList;
    private List<Item> itemList;

    public Job(String jobName, int houseNumber, String street, String city, String province, String country, String postalCode, Customer customer, String description, Date dateStarted, Date dateFinished, int balance, String status, List<Report> reportList, List<User> userList, List<Item> itemList) {
        this.jobName = jobName;
        this.address = new Address();
        setHouseNumber(houseNumber);
        setStreet(street);
        setCity(city);
        setProvince(province);
        setCountry(country);
        setPostalCode(postalCode);
        this.customer = customer;
        this.description = description;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
        this.balance = balance;
        this.status = status;
        this.reportList = reportList;
        this.userList = userList;
        this.itemList = itemList;
    }
    
    public Job() {
        this.jobName = "";
        this.address = new Address();
        this.customer = new Customer();
        this.description = "";
        this.dateStarted = new java.util.Date(Calendar.getInstance().getTime().getTime());
        this.dateFinished = new java.util.Date(Calendar.getInstance().getTime().getTime());
        this.balance = 0;
        this.status = "";
        this.reportList = new ArrayList<>();
        this.userList = new ArrayList<>();
        this.itemList = new ArrayList<>();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
