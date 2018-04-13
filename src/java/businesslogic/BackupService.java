package businesslogic;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Backup Service handles all the operations regarding the Backup Process.
 * 
 */
public class BackupService {
    private static BackupService backupService;
    private ScheduledExecutorService ses;
    private boolean isRunning; 
    
    /**
     * Returns status of backupService.
     *
     * @return backupService
     */
    public static BackupService getInstance() {
        if (backupService == null) {
            backupService = new BackupService();
        }
        
        return backupService;
    }
    
    private BackupService() {
    }
    
    /**
     * Retrieves list of existing backup files in specified directory
     *
     * @return list of backup files as Strings
     */
    public List<String> getBackupFileList() {
        
        File folder = null;
        
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            folder = new File("c:/cap/backups/");
        }
        else {
            //for linux;
        }
        
        File[] listOfFiles = folder.listFiles();
        List<String> backupFileList = new ArrayList<>();
       
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().contains("sql")) {
                String fileName = file.getName();
                
                fileName = fileName.replaceAll(".sql", "");
                fileName = fileName.replaceAll("backup-", "");
                
                backupFileList.add(fileName);
            }
        }
        
        return backupFileList;
    }
    
    /**
     * Creates backup of current state of database
     *
     * @return status regarding backup process
     */
    public String makeBackup() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");  //yyyy-MM-dd-HH-mm-ss
        String now = formatter.format(new Date());
        String backupFileName = "backup-"+ now;
        String line = "";
        
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            line = "cmd /c c:\\xampp\\mysql\\bin\\mysqldump --databases capstonedb -h localhost -u root -ppassword --result-file=c:\\cap\\backups\\" + backupFileName + ".sql";
        }
        else {
            //for linux
        }

        Process runtimeProcess = null;
        String message = "error";

        try {
            runtimeProcess = Runtime.getRuntime().exec(line);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                message = "ok";
            } 

        } catch (IOException ex) {
            Logger.getLogger(BackupService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BackupService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return message;
    }
    
    /**
     * Restores backup from selected file specified in parameter
     *
     * @param backupFileName the name of the backup file as a String
     * @return status regarding restoration process
     */
    public String restoreBackup(String backupFileName) {
        String line = "";

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            line = "cmd /c c:\\cap\\backups\\restore.bat " + backupFileName;
        }
        else {
            //for linux
        }

        Process runtimeProcess = null;
        String message = "unexpected error while attempting to restore backup.";

        try {
            runtimeProcess = Runtime.getRuntime().exec(line);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                message = "Restoring Backup ok!";
            } 

        } catch (IOException ex) {
            Logger.getLogger(BackupService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BackupService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return message;
    }
	
    /**
     * Starts up the automatic back up service
     * 
     * @return message
     */
    private String startBackupper() {
        ses = Executors.newSingleThreadScheduledExecutor();
        
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                makeBackup();
            }
        }, 0, 60*60*24, TimeUnit.SECONDS); //every day
        isRunning = true;
        return "backupper service started!";
    }
	
    /**
     * Halts automatic backup process indefinitely.
     * 
     * @return  message
     */
    private String stopBackupper() {
        ses.shutdown();
        isRunning = false;
        return "backupper service stopped!";
    }
    
    /**
     * Checks if the automatic backupService is currently turned on and running.
     *
     * @return boolean of whether it is running
     */
    public boolean isBackupperServiceRunning() {
        return isRunning;    
    }
    
    /**
     * Control method for either starting or stopping the automatic back service.
     *
     * @return either message
     */
    public String toggleBackupper() {
        if (!isRunning) {
            return startBackupper();
        }
        else {
            return stopBackupper();
        }
    } 
}
