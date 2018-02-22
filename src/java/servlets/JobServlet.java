/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.JobService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "JobServlet", urlPatterns = {"/JobServlet"})
public class JobServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        //doesnt allow users with out an account to get to this page
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");

            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Variables
        String action = request.getParameter("action");
        String selectedJobName = request.getParameter("selectedJobName");

        String errorMessage = "";

        //Adding an Item 
        if (action != null && action.equals("add")) {
            String jobName = request.getParameter("jobName");
            String addressId = request.getParameter("addressId");
            String customerName = request.getParameter("customerName");
            String reportName = request.getParameter("reportName");
            String description = request.getParameter("description");
            //data?
            String dateStarted = request.getParameter("dateStarted");
            String dateFinished = request.getParameter("dateFinished");
            String balance = request.getParameter("balance");
            String status = request.getParameter("status");

        }
        if (action != null && action.equals("delete")) {
            if (selectedJobName != null) {
                JobService jobService = new JobService();
                jobService.delete(selectedJobName);
                errorMessage = "You deleted a Job.";
                //request.setAttribute("errorMessage", "You deleted an item.");

            } else {
                request.setAttribute("errorMessage", "You do not have the authority to DELETE me.");
                getServletContext().getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("edit")) {
            String jobName = request.getParameter("jobName");
            String addressId = request.getParameter("addressId");
            String customerName = request.getParameter("customerName");
            String reportName = request.getParameter("reportName");
            String description = request.getParameter("description");
            java.util.Date dateStart;
            java.util.Date dateFinish;
            java.sql.Date sqlStart= null;
            java.sql.Date sqlFinish= null;
            
            //parse date
            String dateStarted = request.getParameter("dateStarted");
            String dateFinished = request.getParameter("dateFinished");
            try {
                
                dateStart = new SimpleDateFormat("MM/dd/yyyyHH:mm:ss", Locale.ENGLISH).parse(dateStarted);
                sqlStart = DataConverter.javaDate(dateStart);
                dateFinish = new SimpleDateFormat("MM/dd/yyyyHH:mm:ss", Locale.ENGLISH).parse(dateFinished);
                sqlFinish = DataConverter.javaDate(dateFinish);
            } catch (ParseException ex) {
                Logger.getLogger(JobServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String balance = request.getParameter("balance");
            String status = request.getParameter("status");

            if (jobName != null && !jobName.isEmpty() && addressId != null && !addressId.isEmpty() && customerName != null
                    && !customerName.isEmpty() && reportName != null && !reportName.isEmpty() && description != null && !description.isEmpty()
                    && balance != null && !balance.isEmpty() && dateStarted != null && dateFinished != null && status != null) {
                JobService jobService = new JobService();
                jobService.edit(jobName, addressId, customerName, reportName, description,
                        sqlStart, sqlFinish, balance, status);
                request.setAttribute("errorMessage", "You edited your Job");
            } else {
                request.setAttribute("errorMessage", "You can not edit");
                getServletContext().getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
                return;

            }
        }

    }
}
