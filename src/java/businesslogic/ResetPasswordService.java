package businesslogic;

import dataaccess.PCRBroker;
import domainmodel.PasswordChangeRequest;
import java.util.Date;
import utilities.HashingUtil;


public class ResetPasswordService {

    public PasswordChangeRequest getByUUID(String pcrUUID){
        return PCRBroker.getInstance().getByUUID(hash(pcrUUID));
    }

    private String hash(String uuid) {
        return HashingUtil.hash(uuid);
    }
      
    public String delete(String pcrUUID) {
        PasswordChangeRequest pcr = PCRBroker.getInstance().getByUUID(hash(pcrUUID));
        return PCRBroker.getInstance().delete(pcr);
    }

    public String insert(String pcrUUID, String userName) {
        PasswordChangeRequest pcr = new PasswordChangeRequest();
        Date date = new Date();
        
        pcr.setUserName(userName);
        pcr.setPcrUUID(hash(pcrUUID));
        pcr.setPcrTime(date);
    
        return PCRBroker.getInstance().insert(pcr);
    }
}
