package domainmodel;

import java.io.Serializable;
import java.util.Date;

public class PasswordChangeRequest implements Serializable {
    private String pcrUUID;
    private Date pcrTime;
    private String userName;

    public PasswordChangeRequest(String pcrUUID, Date pcrTime, String userName) {
        this.pcrUUID = pcrUUID;
        this.pcrTime = pcrTime;
        this.userName = userName;
    }
    
    public PasswordChangeRequest() {
    }

    public String getPcrUUID() {
        return pcrUUID;
    }

    public void setPcrUUID(String pcrUUID) {
        this.pcrUUID = pcrUUID;
    }

    public Date getPcrTime() {
        return pcrTime;
    }

    public void setPcrTime(Date pcrTime) {
        this.pcrTime = pcrTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
