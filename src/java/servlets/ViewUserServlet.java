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

public class ViewUserServlet extends HttpServlet {
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;
        }
        
        UserService userService = new UserService();

        List<User> userList = userService.searchUser("");
        if (userList == null) {
            request.setAttribute("message", "Employee not found. This seems like a database connection error.");
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
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;
        }
        
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
                request.setAttribute("message", "Employee not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
                return;
            }

            request.setAttribute("user", user);

            request.getRequestDispatcher("/WEB-INF/viewUser.jsp").forward(request, response);
            return;
        }
        else if (action != null && action.equals("save")) {
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
                String email = request.getParameter("email");
                String hourlyRate = request.getParameter("hourlyRate");

                String status = "";

                UserService userService = new UserService();

                status = userService.update(userName, houseNumber, street, city, province, country, postalCode, phoneNumberList, password, firstName, lastName, role, email, hourlyRate);

                request.setAttribute("message", status);

                User user = userService.getByUserName(userName);
                if (user == null) {
                    request.setAttribute("message", "Employee not found. This seems like a database connection error.");
                    getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
                    return;
                }

                request.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/WEB-INF/viewUser.jsp").forward(request, response);
                return;
            }
            else {
                request.setAttribute("message", "You don't have privileges to update an Employee.");
                getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
            }
        }
    }
}
