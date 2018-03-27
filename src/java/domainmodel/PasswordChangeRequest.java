/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainmodel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 725899
 */
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
