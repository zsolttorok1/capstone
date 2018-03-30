/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.UserService;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 742227
 */
@WebServlet(name = "BackupServlet", urlPatterns = {"/BackupServlet"})
public class BackupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        User user1 = new User();
        UserService ns1 = new UserService();
        user1 = ns1.getByUserName(user);
        //doesnt allow users with out an account to get to this page
        //need to add only owner can see
        if (session.getAttribute("user") == null || user1.getRole().equalsIgnoreCase("Manager") || user1.getRole().equalsIgnoreCase("Employee")) {
            response.sendRedirect("login");

            return;
        }
*/
        request.getRequestDispatcher("/WEB-INF/backup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //my dumping created first createsx the datafiles that the restore is saving
        //loader save---restore user selects backup file from database in folder and date
        String dbName = "CapstoneDB";
        String dbUser = "root";
        String dbPass = "password";
        String[] executeCmd;

        executeCmd = new String[]{"C:\\xampp\\mysql\\bin\\mariabackup", "--prepare", "--target-dir", "C:/temp/backup" , "--user", dbName ,"--password" , dbPass};
        //"mariabackup", "--prepare", "--target-dir", "C:/temp/backup" + "--user"+ dbName "--password" + dbPass
        
        
        
        //Run the executeCmd on the commanline
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        Process runtimeProcess1 = Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqladmin -u root -ppassword shutdown");
        Process runtimeProcess2 =Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mariabackup --copy-back --target-dir C:/temp/backup --user root --password password");
        Process runtimeProcess3 =Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqld");
        
        int processComplete=0;
        try {
            processComplete = runtimeProcess.waitFor();
            processComplete = runtimeProcess1.waitFor();
            processComplete = runtimeProcess2.waitFor();
            processComplete = runtimeProcess3.waitFor();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(BackupServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (processComplete == 0) {
            request.setAttribute("errorMessage", "worked");

        } else {
            request.setAttribute("errorMessage", "didnt work");
            //
        }
    request.getRequestDispatcher("/WEB-INF/backup.jsp").forward(request, response);
    }
}