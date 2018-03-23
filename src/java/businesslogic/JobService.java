package businesslogic;

import dataaccess.JobBroker;
import domainmodel.Job;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobService {

//    public String addJob(String jobName, String addressId, String customerName, String reportName, String description,
//            Date dateStarted, Date dateFinished, String balance, String status) {
//        //check for nulls
//        if (jobName == null || addressId == null || customerName == null || reportName == null || description == null
//                || dateStarted == null || dateFinished == null || balance == null || status == null || jobName.isEmpty()
//                || addressId.isEmpty() || customerName.isEmpty() || reportName.isEmpty() || description.isEmpty()
//                || balance.isEmpty()) {
//            //could not check to see if dateStarted could be empty....
//            return "error";
//        }
//
//        //check for values being valid
//        int intAddress = 0;
//        int intBalance = 0;
//        //try catch this
//        try {
//            intAddress = Integer.parseInt(addressId);
//            intBalance = Integer.parseInt(balance);
//
//        } catch (NumberFormatException ex) {
//            Logger.getLogger(JobBroker.class.getName()).log(Level.SEVERE, null, ex);
//            return "error";
//        }
//        Job job = new Job(jobName, intAddress, customerName, reportName, description, dateStarted, dateFinished,
//                intBalance, status);
//
//        JobBroker jobBroker = new JobBroker();
//        return jobBroker.insert(job);
//    }
//
//    public Job viewJob(String jobName) {
//        JobBroker jobBroker = new JobBroker();
//
//        Job job = jobBroker.getByName(jobName);
//
//        return job;
//    }
//
//    public List<Job> searchJob(String keyword) {
//        JobBroker jobBroker = new JobBroker();
//
//        //this always return all items for now
//        return jobBroker.getAll();
//    }
//
//    public String edit(String jobName, String addressId, String customerName, String reportName, String description,
//            Date dateStarted, Date dateFinished, String balance, String status) {
//        JobBroker jobBroker = new JobBroker();
//        Job job = jobBroker.getByName(jobName);
//
//        job.setJobName(jobName);
//
//        int intAddress = 0;
//        int intBalance = 0;
//        //try catch this
//        try {
//            intAddress = Integer.parseInt(addressId);
//            intBalance = Integer.parseInt(balance);
//
//        } catch (NumberFormatException ex) {
//            Logger.getLogger(JobBroker.class.getName()).log(Level.SEVERE, null, ex);
//            return "error";
//        }
//
//        job.setAddressId(intAddress);
//        job.setCustomerName(customerName);
//        job.setReportName(reportName);
//        job.setDescription(description);
//        job.setDateStarted(dateStarted);
//        job.setDateFinished(dateFinished);
//        job.setBalance(intBalance);
//        job.setStatus(status);
//
//        return jobBroker.update(job);
//    }
//
//    public String delete(String jobName) {
//        JobBroker jobBroker = new JobBroker();
//        Job deletedJob = jobBroker.getByName(jobName);
//        return jobBroker.delete(deletedJob);
//    }
//
//    private int parseInteger(int addressId, int balance) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public List<Job> getByUser(String keyword) {
//        JobBroker jobBroker = new JobBroker();
//
//        //this always return all items for now
//        return jobBroker.getJobByUser(keyword);
//    }
}
