package servlets;

import businesslogic.BackupService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BackupServlet", urlPatterns = {"/BackupServlet"})
public class BackupServlet extends HttpServlet {
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null || !session.getAttribute("role").equals("owner")) {
            response.sendRedirect("login");
            return;
        }
        
        String backupServiceState = "off";
        if (!BackupService.getInstance().isBackupperServiceRunning()) {
            backupServiceState = "on";
        }

        request.setAttribute("backupServiceState", backupServiceState);
        request.setAttribute("backupFileList",  BackupService.getInstance().getBackupFileList());
        request.setAttribute("message", "is Backupper Service running? " + BackupService.getInstance().isBackupperServiceRunning());
        request.getRequestDispatcher("/WEB-INF/backup.jsp").forward(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null || !session.getAttribute("role").equals("owner")) {
            response.sendRedirect("login");
            return;
        }
        
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