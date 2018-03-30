package businesslogic;

import dataaccess.CustomerBroker;
import dataaccess.JobBroker;
import domainmodel.Job;
import java.util.List;
import utilities.DataConverter;

public class JobService {
    
    public String insert(String jobName, String houseNumber, String street, String city, String province, String country, String postalCode, String customerId, String description, String dateStarted, String dateFinished, String balance, String status) {
    
        Job job = build(jobName, houseNumber, street, city, province, country, postalCode, customerId, description, dateStarted, dateFinished, balance, status);
        
        String statusOutput = validate(job);
        
        if (statusOutput != null && statusOutput.equals("ok")) {
            JobBroker jobBroker = JobBroker.getInstance();
            statusOutput = jobBroker.insert(job);
        }

        return statusOutput; 
    }

    public Job getByJobName(String jobName) {
        
        if (jobName != null && !jobName.isEmpty()) {
            JobBroker jobBroker = JobBroker.getInstance();
            return jobBroker.getByName(jobName);
        }
        else {
            return null;
        }
    }
    
    public List<Job> searchJob(String keyword) {
        JobBroker jobBroker = JobBroker.getInstance();
        List<Job> jobList = null;
        
        if (!keyword.isEmpty()) {
            jobList = jobBroker.search(keyword);
        }
        else {
            jobList = jobBroker.getAll();
        }
        
        if (jobList == null)
            return null;
                
        return jobList;
    }

    public String update(String jobName, String houseNumber, String street, String city, String province, String country, String postalCode, String customerId, String description, String dateStarted, String dateFinished, String balance, String status) {
        Job job = build(jobName, houseNumber, street, city, province, country, postalCode, customerId, description, dateStarted, dateFinished, balance, status);
        
        return update(job);
    }
    
    public String update(Job jobNew) {
        JobBroker jobBroker = JobBroker.getInstance();
        Job job = null;
        String status = "";
        
        if (jobNew.getJobName() != null) 
            job = jobBroker.getByName(jobNew.getJobName());
        else 
            return "invalid jobName";
        
        if (job == null)
            return "jobname not found while attempting to update. Check database connection.";
        
        //prepare changed attributes on the updatable User
        if (jobNew.getHouseNumber() > 0 )
            job.setHouseNumber(jobNew.getHouseNumber());
        if (jobNew.getStreet() != null && !jobNew.getStreet().isEmpty() )
            job.setStreet(jobNew.getStreet());
        if (jobNew.getCity() != null && !jobNew.getCity().isEmpty() )
            job.setCity(jobNew.getCity());
        if (jobNew.getProvince() != null && !jobNew.getProvince().isEmpty() )
            job.setProvince(jobNew.getProvince());
        if (jobNew.getCountry() != null && !jobNew.getCountry().isEmpty() )
            job.setCountry(jobNew.getCountry());
        if (jobNew.getPostalCode() != null && !jobNew.getPostalCode().isEmpty() )
            job.setPostalCode(jobNew.getPostalCode());
        if (jobNew.getCustomer()!= null && jobNew.getCustomer().getCustomerId() > 0)
            job.setCustomer(jobNew.getCustomer());
        if (jobNew.getDescription()!= null && !jobNew.getDescription().isEmpty() )
            job.setDescription(jobNew.getDescription());
        if (jobNew.getDateStarted()!= null && jobNew.getDateStarted().getTime() < 0 )
            job.setDateStarted(jobNew.getDateStarted());
        if (jobNew.getDateFinished()!= null && jobNew.getDateFinished().getTime() < 0 )
            job.setDateFinished(jobNew.getDateFinished());
        if (jobNew.getBalance() >= 0 )
            job.setBalance(jobNew.getBalance());
        if (jobNew.getStatus() != null && !jobNew.getStatus().isEmpty() )
            job.setStatus(jobNew.getStatus());
       
        //make sure that the new attribute values are valid, before updating.
        status = validate(job);
        if (status != null && status.equals("ok")) {
            return jobBroker.update(job);
        }
        else {
            return status;
        }
    }
      
    private String validate(Job job) {
        String status = "";
        
        if (job == null || job.getJobName().isEmpty()) {
            status += "invalid jobName ";
        }
        if (job.getHouseNumber() <= 0) {
            status += "invalid houseNumber ";
        }
        if (job.getStreet() == null || job.getStreet().isEmpty()) {
            status += "invalid street ";
        }
        if (job.getCity() == null || job.getCity().isEmpty()) {
            status += "invalid city ";
        }
        if (job.getProvince() == null || job.getProvince().isEmpty()) {
            status += "invalid province ";
        }
        if (job.getCountry() == null || job.getCountry().isEmpty()) {
            status += "invalid country ";
        }
        if (job.getPostalCode() == null || job.getPostalCode().isEmpty()) {
            status += "invalid postalCode ";
        }
        if (job.getCustomer() == null || job.getCustomer().getCustomerId() <= 0) {
            status += "invalid Customer ";
        }
        if (job.getDescription() == null || job.getDescription().isEmpty()) {
            status += "invalid Description ";
        }
        if (job.getDateStarted() == null || DataConverter.javaDateToString(job.getDateStarted()).equals("01/01/1975")) {
            status += "invalid Starting Date ";
        }
        if (job.getDateFinished() == null || DataConverter.javaDateToString(job.getDateFinished()).equals("01/01/1975")) {
            status += "invalid Finishing Date ";
        }
        if (job.getBalance() < 0) {
            status += "invalid Balance ";
        }
        if (job.getStatus() == null || job.getPostalCode().isEmpty()) {
            status += "invalid Status ";
        }
        
        if (status.isEmpty()) {
            return "ok";
        }
        else { 
            return status;
        }
    }
    
    private Job build(String jobName, String houseNumber, String street, String city, String province, String country, String postalCode, String customerId, String description, String dateStarted, String dateFinished, String balance, String status) {
        Job job = new Job();
       
        if (jobName != null && !jobName.isEmpty()) {
            job.setJobName(jobName);
        }
        if (houseNumber != null && !houseNumber.isEmpty()) {
            try {
                int intHouseNumber = Integer.parseInt(houseNumber);
                job.setHouseNumber(intHouseNumber);
            } catch (NumberFormatException ex) {
                job.setHouseNumber(-1);
            }
        }
        if (street != null && !street.isEmpty()) {
            job.setStreet(street);
        }
        if (city != null && !city.isEmpty()) {
            job.setCity(city);
        }
        if (province != null && !province.isEmpty()) {
            job.setProvince(province);
        }
        if (country != null && !country.isEmpty()) {
            job.setCountry(country);
        }
        if (postalCode != null && !postalCode.isEmpty()) {
            job.setPostalCode(postalCode);
        }
        if (customerId != null && !customerId.isEmpty()) {
            try {
                int intCustomerId = Integer.parseInt(customerId);
                job.setCustomer(CustomerBroker.getInstance().getById(intCustomerId));
            } catch (Exception ex) {
                job.setHouseNumber(-1);
            }
        }
        if (description != null && !description.isEmpty()) {
            job.setDescription(description);
        }
        if (dateStarted != null && !dateStarted.isEmpty()) {
            job.setDateStarted(DataConverter.stringDateToJava(dateStarted));
        }
        if (dateFinished != null && !dateFinished.isEmpty()) {
            job.setDateFinished(DataConverter.stringDateToJava(dateFinished));
        }
        if (balance != null && !balance.isEmpty()) {
            try {
                int intBalance = Integer.parseInt(balance);
                job.setBalance(intBalance);
            } catch (NumberFormatException ex) {
                job.setBalance(-1);
            }
        }
        if (status != null && !status.isEmpty()) {
            job.setStatus(status);
        }
            
        return job;
    }
    
    public String delete(String jobName) {
        JobBroker jobBroker = JobBroker.getInstance();
        Job deletedJob = jobBroker.getByName(jobName);
        
        return jobBroker.delete(deletedJob);
    }
}
