/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.ItemService;
import businesslogic.UserService;
import domainmodel.Item;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 685442
 */
public class ViewUserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userService = new UserService();

        List<User> userList = userService.searchUser("");
        if (userList == null) {
            request.setAttribute("message", "User not found. This seems like a database connection error.");
        }

        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String userName = request.getParameter("userName");
        
        if (userName == null || userName.isEmpty()) {
            request.setAttribute("message", "invalid userName.");
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
            return;
        }
        
        if (action != null && action.equals("view")) {
  
            UserService userService = new UserService();
            User user = new User();
            user = userService.getByUserName(userName);

            if (user == null) {
                request.setAttribute("message", "User not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
                return;
            }

            request.setAttribute("user", user);

            request.getRequestDispatcher("/WEB-INF/viewUser.jsp").forward(request, response);
            return;
        }
        else if (action != null && action.equals("save")) {
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
            String email = request.getParameter("email");
            String hourlyRate = request.getParameter("hourlyRate");
            
            String status = "";
            
            UserService userService = new UserService();
            
            status = userService.update(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstName, lastName, role, email, hourlyRate);
            
            request.setAttribute("message", status);
            
            User user = userService.getByUserName(userName);
            if (user == null) {
                request.setAttribute("message", "User not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
                return;
            }

            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/viewUser.jsp").forward(request, response);
            return;
        }
    }
}
