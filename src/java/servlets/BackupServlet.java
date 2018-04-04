/*Reference: https://stackoverflow.com/questions/1955268/importing-a-mysql-database-dump-programmatically-through-java
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.BackupService;
import businesslogic.UserService;
import domainmodel.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BackupServlet", urlPatterns = {"/BackupServlet"})
public class BackupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String backupServiceState = "off";
        if (!BackupService.getInstance().isBackupperServiceRunning()) {
            backupServiceState = "on";
        }

        request.setAttribute("backupServiceState", backupServiceState);
        request.setAttribute("backupFileList",  BackupService.getInstance().getBackupFileList());
        request.setAttribute("message", "is Backupper Service running? " + BackupService.getInstance().isBackupperServiceRunning());
        request.getRequestDispatcher("/WEB-INF/backup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String backupFileName = request.getParameter("backupFileName");
        String action = request.getParameter("action");
        String message = "";
        
        if (action != null && action.equals("restore")) {
            message = BackupService.getInstance().restoreBackup(backupFileName);
        }
        else if (action != null && action.equals("toggle")) {
            message = BackupService.getInstance().toggleBackupper();
        }
        
        String backupServiceState = "off";
        if (!BackupService.getInstance().isBackupperServiceRunning()) {
            backupServiceState = "on";
        }

        request.setAttribute("backupServiceState", backupServiceState);
        request.setAttribute("message", message);
        request.setAttribute("backupFileList",  BackupService.getInstance().getBackupFileList());
        request.getRequestDispatcher("/WEB-INF/backup.jsp").forward(request, response);
    }
}