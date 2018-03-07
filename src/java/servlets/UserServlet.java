/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.ItemService;
import businesslogic.UserService;
import domainmodel.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        
        UserService userService = new UserService();
        List<User> userList = userService.searchUser("");
        
        request.setAttribute("userList", userList);

        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
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
        
//        Map<String, String[]> parameters = request.getParameterMap();
        
        String action = request.getParameter("action");
        
        String selectedUserName = request.getParameter("selectedUsername");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String role = request.getParameter("role");
        String address = request.getParameter("address");
        //fake array
        ArrayList<Integer> arrayPhoneIdNew = new ArrayList();
        
        int phoneId = 0;
        
        String email = request.getParameter("email");
        
        int hourlyRate = 0;
        int hours = 0;

        HttpSession session1 = request.getSession();
        String user1 = (String) session1.getAttribute("user");

        try {

            if (action.equals("delete")) {
                us.delete(username);
                //request.setAttribute("errorMessage", "User Deleted");
                //HttpSession session = request.getSession();
                //session.invalidate();
            } else if (action.equals("edit")) {
                String username1 = request.getParameter("username");
                String password1 = request.getParameter("password");
                String firstname1 = request.getParameter("firstName");
                String lastname1 = request.getParameter("lastName");
                String role1 = request.getParameter("role");
                //String phoneId = request.getParameter("phoneId");
                String email1 = request.getParameter("email");
                String address1 = request.getParameter("address");
                
                //fake array
                ArrayList<Integer> arrayPhoneId = new ArrayList();
                
                //changed
                hourlyRate = 0;
                hours = 0;

                //us.edit(username1, address1, arrayPhoneId, password, firstname1, lastname1, role1, email, hourlyRate);
                //request.setAttribute("errorMessage", "User Edited");
                //request.setAttribute("Change", "User Edited");
                //UserService us = new UserService();
                // HttpSession session = request.getSession();
                //String selectedUser = (String) session.getAttribute("user");
                //List of Users list the users out here
                //request.setAttribute("users", users);
                getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                return;
            } else if (action.equals("editPhone")) {
                //need to find a way to add this
            } else if (action.equals("editHours")) {
                //need to find a way to add this
            }else if (action.equals("add")) {
                //TODO get role not null
                //us.addUser(username, address, arrayPhoneIdNew, password, firstname, lastname, role, email, hourlyRate);
                request.setAttribute("errorMessage", "User Added");
                //might have to change userserive to accpet role hmm cant do that might have to setRole
            }
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Whoops.  Could not perform that action.");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
        return;

    }
}
