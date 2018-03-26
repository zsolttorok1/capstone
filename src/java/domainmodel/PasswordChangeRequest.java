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
    private String pcr_id;
    private Date pcr_time;
    private String userName;

    public PasswordChangeRequest(String pcr_id, Date pcr_time, String userName) {
        this.pcr_id = pcr_id;
        this.pcr_time = pcr_time;
        this.userName = userName;
    }
    
    public PasswordChangeRequest() {
    }

    public String getPcr_id() {
        return pcr_id;
    }

    public void setPcr_id(String pcr_id) {
        this.pcr_id = pcr_id;
    }

    public Date getPcr_time() {
        return pcr_time;
    }

    public void setPcr_time(Date pcr_time) {
        this.pcr_time = pcr_time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
