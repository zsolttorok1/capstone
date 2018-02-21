package dataaccess;

import database.ConnectionPool;
import domainmodel.Job;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobBroker {
        public Job getByName(String jobName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Job job = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM job WHERE job_name = ?");
            pstmt.setString(1, jobName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String jobName2 = rs.getString("JOB_NAME");
                int addressId = rs.getInt("ADDRESS_ID");    
                String customerName = rs.getString("CUSTOMER_NAME");
                String reportName = rs.getString("REPORT_NAME");
                String description = rs.getString("DESCRIPTION");
                Date dateStarted = rs.getDate("DATE_STARTED");
                Date dateFinished = rs.getDate("DATE_FINISHED");
                int balance = rs.getInt("BALANCE");
                String status = rs.getString("STATUS");

                job = new Job(jobName2, addressId, customerName, reportName, description,
                dateStarted, dateFinished, balance, status);
            }
            pool.freeConnection(connection);

        } catch (SQLException ex) {
            Logger.getLogger(JobBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return job;

    }

    public List<Job> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        List<Job> jobList = new ArrayList<>();
        Job job = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM job");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String jobName = rs.getString("JOB_NAME");
                int addressId = rs.getInt("ADDRESS_ID");    
                String customerName = rs.getString("CUSTOMER_NAME");
                String reportName = rs.getString("REPORT_NAME");
                String description = rs.getString("DESCRIPTION");
                Date dateStarted = rs.getDate("DATE_STARTED");
                Date dateFinished = rs.getDate("DATE_FINISHED");
                int balance = rs.getInt("BALANCE");
                String status = rs.getString("STATUS");

                job = new Job(jobName, addressId, customerName, reportName, description,
                dateStarted, dateFinished, balance, status);
                jobList.add(job);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JobBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return jobList;
    }

    public String insert(Job job) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String result = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement("select insert_job_func(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, job.getJobName());
            pstmt.setInt(2, job.getAddressId());
            pstmt.setString(3, job.getCustomerName());
            pstmt.setString(4, job.getReportName());
            pstmt.setString(5, job.getDescription());
            pstmt.setDate(6, job.getDateStarted());
            pstmt.setDate(7, job.getDateFinished());
            pstmt.setInt(8, job.getBalance());
            pstmt.setString(9, job.getStatus());
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result = rs.getString(1);
            }
            pool.freeConnection(connection);

            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(JobBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return result;
    }

    public String update(Job job) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE job SET "
                    + "ADDRESS_ID = ?, CUSTOMER_NAME = ?, REPORT_NAME = ?, DESCRIPTION =?, "
                    + "DATE_STARTED = ?, DATE_FINISHED = ?, BALANCE = ?, STATUS = ? WHERE JOB_NAME =?");
            pstmt.setInt(2, job.getAddressId());
            pstmt.setString(3, job.getCustomerName());
            pstmt.setString(4, job.getReportName());
            pstmt.setString(5, job.getDescription());
            pstmt.setDate(6, job.getDateStarted());
            pstmt.setDate(7, job.getDateFinished());
            pstmt.setInt(8, job.getBalance());
            pstmt.setString(9, job.getStatus());        

            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(JobBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "updated";
    }

    public String delete(Job job) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM job WHERE JOB_NAME = ?");
            pstmt.setString(1, job.getJobName());

            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(JobBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "deleted";
    }
}
