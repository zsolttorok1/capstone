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
        UserService userService = new UserService();
        
        String message = "";
        String action = request.getParameter("action");
        String userName = request.getParameter("userName");

        if (action.equals("delete")) {
            String status = userService.delete(userName);
            request.setAttribute("message", status);
        } else if (action.equals("add")) {
            String houseNumber = request.getParameter("houseNumber");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String province = request.getParameter("province");
            String country = request.getParameter("country");
            String postalCode = request.getParameter("postalCode");
            String[] phoneNumberList = request.getParameterValues("phoneNumberList[]");
            String firstName = request.getParameter("firstName");
            String role = request.getParameter("role");
            String email = request.getParameter("email");
            String hourlyRate = request.getParameter("hourlyRate");

            String status = userService.insert(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, action, firstName, firstName, role, email, hourlyRate);

            request.setAttribute("message", status);
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
