package domainmodel;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Job implements Serializable{
    
    private String jobName;
    private Address address;
    private Customer customer;
    private List<Report> reportList;
    private String description; 
    private Date dateStarted;
    private Date dateFinished;
    private int balance; 
    private String status; 
}
