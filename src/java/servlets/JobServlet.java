package servlets;

import businesslogic.CustomerService;;
import businesslogic.JobService;
import domainmodel.Job;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class JobServlet extends HttpServlet {

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
        
        JobService jobService = new JobService();
        List<Job> jobList = jobService.searchJob("");
        if (jobList == null) {
            request.setAttribute("message", "No jobs found. This seems like a database connection error.");
        }
        
        CustomerService customerList = new CustomerService();
        
        request.setAttribute("jobList", jobList);
        request.setAttribute("customerList", customerList.searchCustomer(""));
        request.getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
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
        
        JobService jobService = new JobService();
        
        String message = "";
        String action = request.getParameter("action");
        String jobName = request.getParameter("jobName");

        if (action.equals("delete")) {
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
                String status = jobService.delete(jobName);
                message = status;
            }
            else {
                message = "You don't have privileges to delete a Job.";
            }
        } else if (action.equals("add")) {
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
                String houseNumber = request.getParameter("houseNumber");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String province = request.getParameter("province");
                String country = request.getParameter("country");
                String postalCode = request.getParameter("postalCode");
                String customerId = request.getParameter("customerId");
                String description = request.getParameter("description");
                String dateStarted = request.getParameter("dateStarted");
                String dateFinished = request.getParameter("dateFinished");
                String balance = request.getParameter("balance");
                String jobStatus = request.getParameter("jobStatus");

                String[] reportList = request.getParameterValues("reportList[]");
                String[] itemList = request.getParameterValues("itemList[]");
                String[] userList = request.getParameterValues("userList[]");

                String status = jobService.insert(jobName, houseNumber, street, city, province, country, postalCode, customerId, description, dateStarted, dateFinished, balance, jobStatus);

                message = status;
            }
            else {
                message = "You don't have privileges to add a Job.";
            }
        }

        List<Job> jobList = jobService.searchJob("");
        if (jobList == null) {
            message = "Job not found. This seems like a database connection error.";
        }
        
        CustomerService customerList = new CustomerService();

        request.setAttribute("message", message);
        request.setAttribute("jobList", jobList);
        request.setAttribute("customerList", customerList.searchCustomer(""));
        request.getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
    }
}
