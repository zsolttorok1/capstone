package domainmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The java bean class that handles the Job object attributes and getter/setter methods. 
 * 
 */
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

    /**
     * Constructor
     *
     * @param jobName unique name of job as a String
     * @param houseNumber house number of job location as a Int
     * @param street street name of job location as a String
     * @param city city of job location as a String
     * @param province of job location as a String
     * @param country country of job location as a String
     * @param postalCode postal code of job location as a String
     * @param customer Customer Object to tie to job
     * @param description description of job as a String
     * @param dateStarted easily parse-able start date of job as a Date
     * @param dateFinished parse-able end date of job as a Date
     * @param balance outstanding monetary balance of job as a Int
     * @param status status of job as a String
     * @param reportList list of report Objects
     * @param userList list of user objects
     * @param itemList list of item objects
     */
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
    
    /**
     * Basic Constructor
     */
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

    /**
     * Retrieves the job name
     *
     * @return job name as a String
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Sets the job name
     *
     * @param jobName name of job as a String
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * house number of job location
     *
     * @return house number as an Int
     */
    public int getHouseNumber() {
        return address.getHouseNumber();
    }

    /**
     * Sets the house number of job
     *
     * @param houseNumber house number of job location as a String
     */
    public void setHouseNumber(int houseNumber) {
        this.address.setHouseNumber(houseNumber);
    }

    /**
     * Retrieves street name of job location
     *
     * @return street name as a String
     */
    public String getStreet() {
        return address.getStreet();
    }

    /**
     * Sets the street name of job
     *
     * @param street  street name of job location as a String
     */
    public void setStreet(String street) {
        this.address.setStreet(street);;
    }

    /**
     * Retrieves city of job
     *
     * @return city as a String
     */
    public String getCity() {
        return address.getCity();
    }

    /**
     * Sets the city of job
     *
     * @param city city of job location as a String
     */
    public void setCity(String city) {
        this.address.setCity(city);;
    }

    /**
     * Retrieves province of job
     *
     * @return province as a String
     */
    public String getProvince() {
        return address.getProvince();
    }

    /**
     * Sets the province of job
     *
     * @param province province of job location as a String
     */
    public void setProvince(String province) {
        this.address.setProvince(province);
    }

    /**
     * Retrieves the country of job
     *
     * @return country as a String
     */
    public String getCountry() {
        return this.address.getCountry();
    }

    /**
     * Sets the country of job
     *
     * @param country country of job location as a String
     */
    public void setCountry(String country) {
        this.address.setCountry(country);
    }

    /**
     * Retrieves the postal code of job
     *
     * @return postal code as a String
     */
    public String getPostalCode() {
        return address.getPostalCode();
    }

    /**
     * Sets postal code of job
     *
     * @param postalCode postal code of job location as a String
     */
    public void setPostalCode(String postalCode) {
        this.address.setPostalCode(postalCode);
    }

    /**
     * Retrieves Customer Object related to job
     *
     * @return Customer Object
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the Customer Object relating to the job
     *
     * @param customer Customer Object to be linked to job
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Retrieves description of job
     *
     * @return description of job as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of job
     *
     * @param description description of job as a String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves Date Object for start date of job
     *
     * @return start date as a Date Object
     */
    public Date getDateStarted() {
        return dateStarted;
    }

    /**
     * Sets the start date of job
     *
     * @param dateStarted start date Object
     */
    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    /**
     * Retrieves Date Object for end date of job
     *
     * @return end date as a Date Object
     */
    public Date getDateFinished() {
        return dateFinished;
    }

    /**
     * Sets the end date of job
     *
     * @param dateFinished end date Object
     */
    public void setDateFinished(Date dateFinished) {
        this.dateFinished = dateFinished;
    }

    /**
     * Retrieves Balance of job
     *
     * @return balance as an Int
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Sets the balance of job
     *
     * @param balance outstanding balance of job as a String
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Retrieves status of job
     *
     * @return status of job as a String
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status of job
     *
     * @param status status of job as a String
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves List of reports linked to job
     *
     * @return list of Report Objects
     */
    public List<Report> getReportList() {
        return reportList;
    }

    /**
     * Sets list of Reports linked to job
     *
     * @param reportList list of Report Objects
     */
    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    /**
     * Retrieves List of Users linked to job
     *
     * @return list of User Objects
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Sets list of Users linked to job
     *
     * @param userList list of User Objects
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * Retrieves List of Items linked to job
     *
     * @return list of items
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Sets list of Items linked to job
     *
     * @param itemList list of Item Objects
     */
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
