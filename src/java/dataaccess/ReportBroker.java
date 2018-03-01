package dataaccess;

import database.ConnectionPool;
import domainmodel.Report;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportBroker {

    public Report getByName(String reportName) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Report report = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM report WHERE report_name = ?");
            pstmt.setString(1, reportName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String reportName2 = rs.getString("REPORT_NAME");
                String description = rs.getString("DESCRIPTION");
                Date dateCreated = rs.getDate("DATE_CREATED");
                String pdfFilePath = rs.getString("PDF_FILEPATH");

                report = new Report(reportName2, description, dateCreated, pdfFilePath);
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
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        List<Report> reportList = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT r.report_name, r.description, r.date_created, r.pdf_filepath "
                    + "FROM `report` r "
                    + "JOIN `job` j ON j.report_name = r.report_name;");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String reportName = rs.getString("REPORT_NAME");
                String description = rs.getString("DESCRIPTION");
                Date dateCreated = rs.getDate("DATE_CREATED");
                String pdfFilePath = rs.getString("PDF_FILEPATH");

                Report report = new Report(reportName, description, dateCreated, pdfFilePath);
                reportList.add(report);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return reportList;
    }

    public String insert(Report report) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String result = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement("select insert_item_func(?, ?, ?, ?)");
            pstmt.setString(1, report.getReportName());
            pstmt.setString(2, report.getDescription());
            pstmt.setDate(3, report.getDateCreated());
            pstmt.setString(4, report.getPdfFilePath());

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

    public String update(Report report) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE report SET "
                    + "DESCRIPTION = ?, DATE_CREATED = ?, PDF_FILEPATH = ? "
                    + "WHERE REPORT_NAME =?");
            pstmt.setString(1, report.getDescription());
            pstmt.setDate(2, report.getDateCreated());
            pstmt.setString(3, report.getPdfFilePath());
            pstmt.setString(4, report.getReportName());

            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ReportBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "updated";
    }

    public String delete(Report report) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM report WHERE REPORT_NAME = ?");
            pstmt.setString(1, report.getReportName());

            int rs = pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReportBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "deleted";
    }
}
