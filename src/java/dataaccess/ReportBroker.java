package dataaccess;

import database.ConnectionPool;
import domainmodel.Job;
import domainmodel.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DataConverter;

public class ReportBroker {
    
    private static ReportBroker reportBroker;
    private ConnectionPool pool;
    private Connection connection;
    
    public static ReportBroker getInstance() {
        if (reportBroker == null) {
            reportBroker = new ReportBroker();
        }
        
        return reportBroker;
    }
    
    private ReportBroker() {
    }
    
    private String getConnection() {
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.getConnection();
            
            if (connection != null) {
                connection.setAutoCommit(false);
                return "ok";
            }
            else {
                return "connection error";
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "connection error";
        }
    }

    public Report getById(int reportId) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        
        Report report = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM report WHERE report_id = ?");
            pstmt.setInt(1, reportId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Date dateCreated = rs.getTime("DATE_CREATED");
                String pdfFilePath = rs.getString("PDF_FILEPATH");

                report = new Report(reportId, null ,dateCreated, pdfFilePath);
            }
            pool.freeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ReportBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return report;
    }

    public List<Report> getAll() {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }

        List<Report> reportList = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                + "SELECT report_id, date_created, pdf_filepath "
                + "     FROM `report`;");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int reportId = rs.getInt("REPORT_ID");
                Date dateCreated = rs.getTime("DATE_CREATED");
                String pdfFilePath = rs.getString("PDF_FILEPATH");

                Report report = new Report(reportId, null, dateCreated, pdfFilePath);
                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return reportList;
    }
    
    public List<Report> getByJobName(String jobName) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }

        List<Report> reportList = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT r.report_id, r.date_created, r.pdf_filepath "
                + "FROM `report` r "
                + "JOIN `job_report` jr ON jr.report_id = r.report_id "
                + "     WHERE jr.job_name = ?");

            pstmt.setString(1, jobName);
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int reportId = rs.getInt("REPORT_ID");
                Date dateCreated = rs.getTime("DATE_CREATED");
                String pdfFilePath = rs.getString("PDF_FILEPATH");

                Report report = new Report(reportId, null, dateCreated, pdfFilePath);
                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return reportList;
    }

    public String generateByJobName(String jobName) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }
        String result = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement("select generate_report_func(?, ?)");
            pstmt.setString(1, jobName);
            pstmt.setString(2, DataConverter.javaDateToSQLDateTime(new Date()));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result = rs.getString(1);
            }
            pool.freeConnection(connection);

            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ReportBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return result;
    }
}
