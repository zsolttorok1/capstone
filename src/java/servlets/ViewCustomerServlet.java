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
import javax.servlet.http.HttpSession;

@WebServlet(name = "ViewCustomerServlet", urlPatterns = {"/ViewCustomerServlet"})
public class ViewCustomerServlet extends HttpServlet {

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
        if (session.getAttribute("userName") == null || !session.getAttribute("role").equals("owner")) {
            response.sendRedirect("login");
            return;
        }
        
        CustomerService customerService = new CustomerService();

        List<Customer> customerList = customerService.searchCustomer("");
        if (customerList == null) {
            request.setAttribute("message", "Customer not found. This seems like a database connection error.");
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
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null || !session.getAttribute("role").equals("owner")) {
            response.sendRedirect("login");
            return;
        }
        
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
            String notes = request.getParameter("notes");
            
            String status = "";
            
            CustomerService customerService = new CustomerService();
            
            status = customerService.update(customerId, houseNumber, street, city, province, country, postalCode, phoneNumberList, firstName, lastName, company, email, position, notes);
            
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
