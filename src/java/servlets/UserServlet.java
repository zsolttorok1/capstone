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

/**
 *
 * @author 742227
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {
         @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("errorMessage", "Logged out");
        }

       // getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        // stop other execution of code

    }
     /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();
        UserService ns = new UserService();
        String action = request.getParameter("action");
        String selectedUserName = request.getParameter("selectedUsername");

        HttpSession session1 = request.getSession();
        String user1 = (String) session1.getAttribute("user");
        try {

            if (action.equals("delete")) {
                //us.update(username1, password1, email1, active1, firstname1, lastname1, role1, company2);
                //request.setAttribute("errorMessage", "User Deleted");
                //HttpSession session = request.getSession();
                //session.invalidate();
            } else if (action.equals("edit")) {
                String username1 = request.getParameter("username");
                String password1 = request.getParameter("username");
                String firstname1 = request.getParameter("username");
                String lastname1 = request.getParameter("username");
                String role1 = request.getParameter("username");
                String phone = request.getParameter("username");
                String email1 = request.getParameter("username");
                String address = request.getParameter("username");

                int hourlyRate = 0;
                int hours = 0;
       

                //us.update(username, password, email, active1, firstname, lastname, role, company);
                //request.setAttribute("errorMessage", "User Edited");
                //request.setAttribute("Change", "User Edited");
         
                //UserService us = new UserService();
                // HttpSession session = request.getSession();
                //String selectedUser = (String) session.getAttribute("user");
                try {
                   // User users = us.get(selectedUser);
                    //request.setAttribute("selectedUser", users);
                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
               //List of Users list the users out here

                //request.setAttribute("users", users);
                getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                return;
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
     
        
    }
}