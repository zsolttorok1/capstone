package domainmodel;

import java.io.Serializable;
import java.util.Date;

/**
 * The java bean class that handles the Report object attributes and getter/setter methods. 
 */
public class Report implements Serializable {

    /**
     * contains the unique ID of the Report
     */
    private int reportId;
    
    /**
     * contains the description of the Report
     */
    private String description;
    
    /**
     * contains the time the report generation happened
     */
    private Date dateCreated;
    
    /**
     * contains the Report PDF file name is being stored at
     */
    private String pdfFilePath;

    /**
     * Constructor
     * @param reportId contains the unique ID of the Report
     * @param description contains the description of the Report
     * @param dateCreated contains the time the report generation happened
     * @param pdfFilePath contains the Report PDF file name is being stored at
     */
    public Report(int reportId, String description, Date dateCreated, String pdfFilePath) {
        this.reportId = reportId;
        this.description = description;
        this.dateCreated = dateCreated;
        this.pdfFilePath = pdfFilePath;
    }

    public Report() {
    }

    /**
     * gets the reportId
     * @return the reportId
     */
    public int getReportId() {
        return reportId;
    }

    /**
     * sets the reportId
     * @param reportId the reportId
     */
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    /**
     * gets the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets the dateCreated
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * sets the the dateCreated
     * @param dateCreated the dateCreated
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * gets the pdfFilePath
     * @return the pdfFilePath
     */
    public String getPdfFilePath() {
        return pdfFilePath;
    }

    /**
     * sets the pdfFilePath
     * @param pdfFilePath the pdfFilePath
     */
    public void setPdfFilePath(String pdfFilePath) {
        this.pdfFilePath = pdfFilePath;
    }

}
