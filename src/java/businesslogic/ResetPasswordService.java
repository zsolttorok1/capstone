package businesslogic;

import dataaccess.PCRBroker;
import domainmodel.PasswordChangeRequest;
import java.util.Date;
import utilities.HashingUtil;

/**
 * This service class handles all the Password Resetting calls from the servlet over the broker classes.
 */
public class ResetPasswordService {

    /**
     * Gets the selected PasswordChangeRequest by the UUID
     * @param pcrUUID the selected UUID to search for
     * @return the found PasswordChangeRequest
     */
    public PasswordChangeRequest getByUUID(String pcrUUID){
        return PCRBroker.getInstance().getByUUID(hash(pcrUUID));
    }

    /**
     * Hashing the provided UUID with the algorithm of choice (currently Keccak512 is selected).
     * @param uuid the UUID about to get hashed.
     * @return the hashed version of UUID
     */
    private String hash(String uuid) {
        return HashingUtil.hash(uuid);
    }
      
    /**
     * Deletes the PasswordChangeRequest, based on the selected UUID
     * @param pcrUUID the UUID to target the PasswordChangeRequest
     * @return "deleted" if everything went ok, "error" if there was an expected issue, "exception" if there was an unexpected issue.
     */
    public String delete(String pcrUUID) {
        PasswordChangeRequest pcr = PCRBroker.getInstance().getByUUID(hash(pcrUUID));
        return PCRBroker.getInstance().delete(pcr);
    }

    /**
     * Creates a new PasswordChangeRequest, based on the selected UUID and userName
     * @param pcrUUID the UUID to target the PasswordChangeRequest to
     * @param userName the userName to target the PasswordChangeRequest to
     * @return "inserted" if everything went ok, "error" if there was an expected issue, "exception" if there was an unexpected issue.
     */
    public String insert(String pcrUUID, String userName) {
        PasswordChangeRequest pcr = new PasswordChangeRequest();
        Date date = new Date();
        
        pcr.setUserName(userName);
        pcr.setPcrUUID(hash(pcrUUID));
        pcr.setPcrTime(date);
    
        return PCRBroker.getInstance().insert(pcr);
    }
}
