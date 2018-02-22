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
@WebServlet(name = "ReportServlet", urlPatterns = {"/ReportServlet"})
public class ReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        User user1 = new User();
        UserService ns1 = new UserService();
        user1 = ns1.viewUser(user);
        //doesnt allow users with out an account to get to this page
        //need to add only owner can see
        if (session.getAttribute("user") == null || user1.getRole().equalsIgnoreCase("Manager") || user1.getRole().equalsIgnoreCase("Employee")) {
            response.sendRedirect("login");

            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String selectedReportName = request.getParameter("selectedUsername");
        if (action.equals("view")) {
            
            
            
        }
        else if (action.equals("generate")) {
            
        
        }
}
}