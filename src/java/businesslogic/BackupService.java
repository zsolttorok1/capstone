/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class BackupService {
    private static BackupService backupService;
    private ScheduledExecutorService ses;
    private boolean isRunning; 
    
    public static BackupService getInstance() {
        if (backupService == null) {
            backupService = new BackupService();
        }
        
        return backupService;
    }
    
    private BackupService() {
    }
    
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
    
    public String makeBackup() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
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
	
    private String startBackupper() {
        ses = Executors.newSingleThreadScheduledExecutor();
        
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                makeBackup();
            }
        }, 0, 10, TimeUnit.SECONDS);
        isRunning = true;
        return "backupper service started!";
    }
	
    private String stopBackupper() {
        ses.shutdown();
        isRunning = false;
        return "backupper service stopped!";
    }
    
    public boolean isBackupperServiceRunning() {
        return isRunning;    
    }
    
    public String toggleBackupper() {
        if (!isRunning) {
            return startBackupper();
        }
        else {
            return stopBackupper();
        }
    } 
}
