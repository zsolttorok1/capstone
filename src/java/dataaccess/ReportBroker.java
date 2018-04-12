package dataaccess;

import database.ConnectionPool;
import domainmodel.Report;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DataConverter;

/**
 * Broker class which communicates with JDBC and handles all the Report information management.
 */
public class ReportBroker {
    /**
     * contains the ReportBroker instance
     */
    private static ReportBroker reportBroker;
    
    /**
     * Reference to the ConnectionPool
     */
    private ConnectionPool pool;
    
    /**
     * Reference to the Connection
     */
    private Connection connection;
    
    /**
     * Singleton method which gets the ReportBroker instance.
     * @return the ReportBroker instance
     */
    public static ReportBroker getInstance() {
        if (reportBroker == null) {
            reportBroker = new ReportBroker();
        }
        
        return reportBroker;
    }
    
    /**
     * Default constructor
     */
    private ReportBroker() {
    }
    
    /**
     * Gets the next free connection from the connection pool.
     * @return "ok" if everything went fine, "connection error" if there was an issue in the process.
     */
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

    /**
     * Gets the Report from the reportId
     * @param reportId the reportId to search for
     * @return the found Report, or null if not found
     */
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

    /**
     * Gets all the Repors from the database
     * @return a List of the received Report objects, or null if there is an issue
     */
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
    
    /**
     * Gets the Report from the jobName
     * @param jobName the jobName to search for
     * @return the found Report, or null if not found
     */
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
                String stringDateCreated = rs.getString("DATE_CREATED");
                Date dateCreated = DataConverter.stringDateTimeToJava(stringDateCreated);
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

    /**
     * Generates the report from the jobName
     * @param jobName the jobName to generate the report from
     * @return a confirmation string if everything went ok, or null if any unexpected issues occurred.
     */
    public String generateByJobName(String jobName) {
        String status = getConnection();
        if (status == null || status.equals("connection error")) {
            return null;
        }

        try {
            PreparedStatement pstmt = connection.prepareStatement("select generate_report_func(?, ?)");
            pstmt.setString(1, jobName);
            pstmt.setString(2, DataConverter.javaDateToSQLDateTime(new Date()));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                status = rs.getString(1);
            }

            //if something unexpected happened, rollback any changes.
            if (status == null || status.equals("error")) {
                connection.rollback();
                return "error";
            }
            //if all good, commit
            else {
                connection.commit();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex1);
                return "exception";
            }
            return "exception";
        } finally {
            pool.freeConnection(connection);
        }

        return status;
    }
}
