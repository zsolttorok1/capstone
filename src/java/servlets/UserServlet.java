/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.UserService;
import domainmodel.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
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
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;
        }
        
        UserService userService = new UserService();
        List<User> userList = userService.searchUser("");
        if (userList == null) {
            request.setAttribute("message", "No users found. This seems like a database connection error.");
        }
        
        request.setAttribute("userList", userList);

        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
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
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;
        }
        
        UserService userService = new UserService();
        
        String message = "";
        String action = request.getParameter("action");
        String userName = request.getParameter("userName");
     
        if (action.equals("delete")) {
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
                String status = userService.delete(userName);
                message = status;
            }
            else {
                message = "You don't have privileges to delete an Employee.";
            }
        } else if (action.equals("add")) {
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
                String houseNumber = request.getParameter("houseNumber");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String province = request.getParameter("province");
                String country = request.getParameter("country");
                String postalCode = request.getParameter("postalCode");
                String[] phoneNumberList = request.getParameterValues("phoneNumberList[]");
                String password = request.getParameter("password");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String role = request.getParameter("role");
                String email = request.getParameter("emailAddress");
                String hourlyRate = request.getParameter("hourlyRate");

                String status = userService.insert(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstName, lastName, role, email, hourlyRate);

                message = status;
            }
            else {
                message = "You don't have privileges to add an Employee.";
            }
        }

        List<User> userList = userService.searchUser("");
        if (userList == null) {
            message = "User not found. This seems like a database connection error.";
        }

        request.setAttribute("message", message);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
    }
}
