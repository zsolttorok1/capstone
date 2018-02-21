package domainmodel;

import java.io.Serializable;
import java.sql.Date;

public class Job implements Serializable{
    
    private String jobName;
    private int addressId;
    private String customerName; 
    private String reportName;
    private String description; 
    private Date dateStarted;
    private Date dateFinished;
    private int balance; 
    private String status; 
    
    public Job(String jobName, int addressId, String customerName, String reportName, String description,
            Date dateStarted, Date dateFinished, int balance, String status){
        this.jobName = jobName;
        this.addressId = addressId;
        this.customerName = customerName; 
        this.reportName = reportName; 
        this.description = description;
        this.dateStarted = dateStarted;
        this.dateFinished = dateFinished;
        this.balance = balance; 
        this.status = status;
    }
    
    public Job(){
        
    }
    
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
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
    
    
}
