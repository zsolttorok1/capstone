/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.JobService;
import domainmodel.Job;
import java.io.IOException;
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
@WebServlet(name = "ViewJobServlet", urlPatterns = {"/ViewJobServlet"})
public class ViewJobServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        JobService jobService = new JobService();

        List<Job> jobList = jobService.searchJob("");
        if (jobList == null) {
            request.setAttribute("message", "Job not found. This seems like a database connection error.");
        }
        
//        request.setAttribute("jobList", jobList);
//        request.getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);

        //testing purposes:
        String jobName = "Brookfield Bathroom on WestTower";
        Job job = jobService.getByJobName(jobName);
        request.setAttribute("job", job);
        request.getRequestDispatcher("/WEB-INF/viewJob.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String jobName = request.getParameter("selectedJobName");
        
        if (jobName == null || jobName.isEmpty()) {
            request.setAttribute("message", "invalid jobName.");
            getServletContext().getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
            return;
        }
        
        if (action != null && action.equals("view")) {
  
            JobService jobService = new JobService();
            Job job = new Job();
            job = jobService.getByJobName(jobName);

            if (job == null) {
                request.setAttribute("message", "Job not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
                return;
            }

            request.setAttribute("job", job);

            request.getRequestDispatcher("/WEB-INF/viewJob.jsp").forward(request, response);
            return;
        }
        
        else if (action != null && action.equals("save")) {
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
            
            String status = "";
            
            JobService jobService = new JobService();
            
            status = jobService.update(jobName, houseNumber, street, city, province, country, postalCode, customerId, description, dateStarted, dateFinished, balance, jobStatus);
            
            request.setAttribute("message", status);
            
            Job job = jobService.getByJobName(jobName);
            if (job == null) {
                request.setAttribute("message", "Job not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
                return;
            }

            request.setAttribute("job", job);
            getServletContext().getRequestDispatcher("/WEB-INF/viewJob.jsp").forward(request, response);
            return;
        }
        
    }
}
