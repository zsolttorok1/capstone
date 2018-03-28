/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.CustomerService;
import domainmodel.Customer;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 742227
 */
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        CustomerService customerService = new CustomerService();
        List<Customer> customerList = customerService.searchCustomer("");
        if (customerList == null) {
            request.setAttribute("message", "No customers found. This seems like a database connection error.");
        }
        
        request.setAttribute("customerList", customerList);

        request.getRequestDispatcher("/WEB-INF/customer.jsp").forward(request, response);
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
        CustomerService customerService = new CustomerService();
        
        String message = "";
        String action = request.getParameter("action");
        String customerName = request.getParameter("customerName");

        if (action.equals("delete")) {
            String status = customerService.delete(customerName);
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
            String lastName = request.getParameter("lastName");
            String companyName = request.getParameter("companyName");
            String email = request.getParameter("emailAddress");
            String position = request.getParameter("position");
            //String notes = request.getParameter("notes");
            String customerNameAdd = firstName + "_" + lastName + houseNumber;
            
            String status = customerService.insert(customerNameAdd, houseNumber, street, city, province, country, postalCode, phoneNumberList, firstName, lastName, companyName, email, position, " ");

            request.setAttribute("message", status);
        }

        List<Customer> customerList = customerService.searchCustomer("");
        if (customerList == null) {
            message = "Customer not found. This seems like a database connection error.";
        }

        request.setAttribute("message", message);
        request.setAttribute("customerList", customerList);
        request.getRequestDispatcher("/WEB-INF/customer.jsp").forward(request, response);
    }
}
