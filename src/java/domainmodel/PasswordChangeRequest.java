package domainmodel;

import java.io.Serializable;
import java.util.Date;

/**
 * The java bean class that handles the PasswordChangeRequest object attributes and getter/setter methods. 
 */
public class PasswordChangeRequest implements Serializable {
    /**
     * the UUID that identifies the request
     */
    private String pcrUUID;
    
    /**
     * contains the time the request happened
     */
    private Date pcrTime;
    
    /**
     * contains the userName of the person who made the request
     */
    private String userName;

    /**
     * Constructor
     * @param pcrUUID the UUID that identifies the request
     * @param pcrTime contains the time the request happened
     * @param userName contains the userName of the person who made the request
     */
    public PasswordChangeRequest(String pcrUUID, Date pcrTime, String userName) {
        this.pcrUUID = pcrUUID;
        this.pcrTime = pcrTime;
        this.userName = userName;
    }
    
    public PasswordChangeRequest() {
    }

    /**
     * gets the pcrUUID
     * @return the pcrUUID
     */
    public String getPcrUUID() {
        return pcrUUID;
    }

    /**
     * sets the pcrUUID
     * @param pcrUUID the pcrUUID
     */
    public void setPcrUUID(String pcrUUID) {
        this.pcrUUID = pcrUUID;
    }

    /**
     * gets the prcTime
     * @return the prcTime
     */
    public Date getPcrTime() {
        return pcrTime;
    }

    /**
     * sets the prcTime
     * @param pcrTime the prcTime
     */
    public void setPcrTime(Date pcrTime) {
        this.pcrTime = pcrTime;
    }

    /**
     * gets the userName
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets the userName
     * @param userName the userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
