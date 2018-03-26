package businesslogic;

import dataaccess.PasswordChangeRequestDB;
import domainmodel.PasswordChangeRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import utilities.HashingUtil;


public class ResetPasswordService {

    private PasswordChangeRequestDB passDB;

    public ResetPasswordService() {
        passDB = new PasswordChangeRequestDB();
    }
  
    public PasswordChangeRequest get(String ID){
        return passDB.get(hash(ID));
    }

    public List<PasswordChangeRequest> getAll() {
        return passDB.getAll();
    }
    
    private String hash(String uuid) {
        return HashingUtil.hash(uuid);
    }
      
    public boolean delete(String ID) {
        PasswordChangeRequest deletedPCR = passDB.get(hash(ID));
        return passDB.delete(deletedPCR);
    }

    public boolean insert(String ID, String username) {
        PasswordChangeRequest pcr = new PasswordChangeRequest();
        UserService us = new UserService();
        Date date = new Date();
            
        try {
            pcr.setOwner(us.getByUsername(username));
        } catch (Exception ex) {
            Logger.getLogger(ResetPasswordService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        pcr.setId(hash(ID));
        pcr.setTime(date);
    
        return passDB.insert(pcr);
    }
}
