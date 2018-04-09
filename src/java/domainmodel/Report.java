package domainmodel;

import java.io.Serializable;
import java.util.Date;

public class Report implements Serializable {

    private int reportId;
    private String description;
    private Date dateCreated;
    private String pdfFilePath;

    public Report(int reportId, String description, Date dateCreated, String pdfFilePath) {
        this.reportId = reportId;
        this.description = description;
        this.dateCreated = dateCreated;
        this.pdfFilePath = pdfFilePath;
    }

    public Report() {
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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
