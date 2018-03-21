/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.CustomerService;
import businesslogic.JobService;
import businesslogic.UserService;
import domainmodel.Customer;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilities.DataConverter;

/**
 *
 * @author 742227
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("errorMessage", "Logged out");
        }
        
        CustomerService customerService = new CustomerService();
        List<Customer> customerList = customerService.searchCustomer("");
        
        request.setAttribute("customerList", customerList);

        request.getRequestDispatcher("/WEB-INF/customer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Variables
        String action = request.getParameter("action");
        String selectedCustomerName = request.getParameter("selectedCustomerName");

        String errorMessage = "";

        //Adding an Customer
        if (action != null && action.equals("add")) {
            String customerName = request.getParameter("customerName");
            String jobName = request.getParameter("jobName");
            //phone
            ArrayList<Integer> phoneId = new ArrayList();
            phoneId.add(Integer.parseInt(request.getParameter("phoneId")));
            String addressId = request.getParameter("address");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String companyName = request.getParameter("companyName");
            String email = request.getParameter("email");
            String position = request.getParameter("position");
            String note = request.getParameter("note");

        }
        if (action != null && action.equals("delete")) {
            if (selectedCustomerName != null) {
                CustomerService customerService = new CustomerService();
                customerService.delete(selectedCustomerName);
                errorMessage = "You deleted a Customer.";
                //request.setAttribute("errorMessage", "You deleted an item.");

            } else {
                request.setAttribute("errorMessage", "You do not have the authority to DELETE me.");
                getServletContext().getRequestDispatcher("/WEB-INF/customer.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("edit")) {
            String customerName = request.getParameter("customerName");
            String jobName = request.getParameter("jobName");
            //phone
            ArrayList<Integer> phoneId = new ArrayList();
            phoneId.add(Integer.parseInt(request.getParameter("phoneId")));
            String addressId = request.getParameter("address");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String companyName = request.getParameter("companyName");
            String email = request.getParameter("email");
            String position = request.getParameter("position");
            String note = request.getParameter("note");

            if (customerName != null && jobName != null && !phoneId.isEmpty() && addressId != null && firstName != null
                    && lastName != null && companyName != null && email != null && position != null && note != null
                    && !customerName.isEmpty() && !jobName.isEmpty() && !addressId.isEmpty() && !firstName.isEmpty()
                    && !lastName.isEmpty() && !companyName.isEmpty() && !email.isEmpty()) {

                CustomerService customerService = new CustomerService();
                customerService.edit(customerName, jobName, phoneId, addressId, firstName,
                        lastName, companyName, email, position, note);
                request.setAttribute("errorMessage", "You edited your Customer");
            } else {
                request.setAttribute("errorMessage", "You can not edit");
                getServletContext().getRequestDispatcher("/WEB-INF/customer.jsp").forward(request, response);
                return;

            }
        }

    }
}
