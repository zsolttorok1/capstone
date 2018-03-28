/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.CustomerService;
import domainmodel.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Steven
 */
@WebServlet(name = "ViewCustomerServlet", urlPatterns = {"/ViewCustomerServlet"})
public class ViewCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CustomerService customerService = new CustomerService();

        String keyword = "anything";
        List<Customer> customerList = customerService.searchCustomer(keyword);
        if (customerList == null) {
            request.setAttribute("message", "Customer not found. This seems like a database connection error.");
        }

        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher("/WEB-INF/viewCustomer.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String customerId = request.getParameter("customerId");
        
        if (customerId == null || customerId.isEmpty()) {
            request.setAttribute("message", "invalid customerId.");
            getServletContext().getRequestDispatcher("/WEB-INF/customer.jsp").forward(request, response);
            return;
        }
        
        if (action != null && action.equals("view")) {
  
            CustomerService customerService = new CustomerService();
            Customer customer = new Customer();
            customer = customerService.getByCustomerId(customerId);

            if (customer == null) {
                request.setAttribute("message", "Customer not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/customer.jsp").forward(request, response);
                return;
            }

            request.setAttribute("customer", customer);

            request.getRequestDispatcher("/WEB-INF/viewCustomer.jsp").forward(request, response);
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
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String company = request.getParameter("company");
            String email = request.getParameter("email");
            String position = request.getParameter("position");
            
            String status = "";
            
            CustomerService customerService = new CustomerService();
            
            status = customerService.update(customerId, houseNumber, street, city, province, country, postalCode, phoneNumberList, firstName, lastName, company, email, position, " ");
            
            request.setAttribute("message", status);
            
            Customer customer = customerService.getByCustomerId(customerId);
            if (customer == null) {
                request.setAttribute("message", "Customer not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/customer.jsp").forward(request, response);
                return;
            }

            request.setAttribute("customer", customer);
            getServletContext().getRequestDispatcher("/WEB-INF/viewCustomer.jsp").forward(request, response);
            return;
        }
        
    }
}
