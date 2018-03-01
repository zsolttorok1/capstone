package domainmodel;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable {

    private String reportName;
    private String description;
    private Date dateCreated;
    private String pdfFilePath;

    public Report(String reportName, String description, Date dateCreated, String pdfFilePath) {
        this.reportName = reportName;
        this.description = description;
        this.dateCreated = dateCreated;
        this.pdfFilePath = pdfFilePath;
    }

    public Report() {

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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPdfFilePath() {
        return pdfFilePath;
    }

    public void setPdfFilePath(String pdfFilePath) {
        this.pdfFilePath = pdfFilePath;
    }

}
