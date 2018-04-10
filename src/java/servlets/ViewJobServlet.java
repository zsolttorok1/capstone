/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.JobService;
import businesslogic.ReportService;
import domainmodel.Item;
import domainmodel.Job;
import domainmodel.Report;
import domainmodel.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Steven
 */
@WebServlet(name = "ViewJobServlet", urlPatterns = {"/ViewJobServlet"})
public class ViewJobServlet extends HttpServlet {

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
            request.setAttribute("message", "Job not found. This seems like a database connection error.");
        }
        
        request.setAttribute("jobList", jobList);
        request.getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;
        }
        
        String save = request.getParameter("save");
        String allocateItem = request.getParameter("allocateItem");
        String assignUser = request.getParameter("assignUser");
        String unallocatedItemName = request.getParameter("unallocatedItemName");
        String unassignedUserName = request.getParameter("unassignedUserName");
        String generate = request.getParameter("generate");
        String jobName = request.getParameter("jobName");
        String reportId = request.getParameter("reportId");
        String message = "";
        
        if (jobName == null || jobName.isEmpty()) {
            request.setAttribute("message", "invalid jobName.");
            getServletContext().getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
            return;
        }
        
        JobService jobService = new JobService();
        Job job = jobService.getByJobName(jobName);
        
        if (job == null) {
            request.setAttribute("message", "Job not found. This seems like a database connection error.");
            getServletContext().getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
            return;
        }
        else if (allocateItem != null) {
            String itemName = request.getParameter("allocatedItemName");
            String quantity = request.getParameter("quantity");
           
            String status = jobService.allocateItem(jobName, itemName, quantity, "");
            message = status;
        }
        else if (unallocatedItemName != null) {
            String status = jobService.unallocateItem(jobName, unallocatedItemName);
            message = status;
        }
        else if (assignUser != null) {
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
                String userName = request.getParameter("assignedUserName");
                String hours = request.getParameter("hours");

                String status = jobService.assignUser(jobName, userName, hours);
                message = status;
            }
            else {
                message = "You don't have privileges to assign an Employee.";
            }
        }
        else if (unassignedUserName != null) {
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
                String status = jobService.unassignUser(jobName, unassignedUserName);
                message = status;
            }
            else {
                message = "You don't have privileges to remove an Employee from a Job.";
            }
        }
        else if (generate != null) {
            if (session.getAttribute("role").equals("owner")) {
                String reportDescription = request.getParameter("reportDescription");
                String status = "";

                ReportService reportService = new ReportService();
                status = reportService.generateFromJob(job, reportDescription);
                message = status;
            }
            else {
                message = "You don't have privileges to generate a Report from a Job.";
            }
        }
        else if (reportId != null){
            if (session.getAttribute("role").equals("owner")) {
                // Find this file id in database to get file name, and file type
                String filePath = "c:\\cap\\reports\\";
                String fileName = new ReportService().getById(reportId).getPdfFilePath();
                filePath = filePath + fileName + ".pdf";

                // You must tell the browser the file type you are going to send
                // for example application/pdf, text/plain, text/html, image/jpg
                response.setContentType("application/pdf");

                // Make sure to show the download dialog
                response.setHeader("Content-disposition","attachment; filename=" + fileName + ".pdf");

                // Assume file name is retrieved from database
                // For example D:\\file\\test.pdf
                File file = new File(filePath);

                // This should send the file to browser
                OutputStream out = response.getOutputStream();
                FileInputStream in = new FileInputStream(file);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0){
                   out.write(buffer, 0, length);
                }
                in.close();
                out.flush();
                return;
            }
            else {
                message = "You don't have privileges to view a Report from a Job.";
            }
        }
        else if (save != null) {
            String houseNumber = null;
            String street = null;
            String city = null;
            String province = null;
            String country = null;
            String postalCode = null;
            String customerId = null;
            String description = null;
            String dateStarted = null;
            String dateFinished = null;
            String balance = null;
            String jobStatus = null;
                
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
                houseNumber = request.getParameter("houseNumber");
                street = request.getParameter("street");
                city = request.getParameter("city");
                province = request.getParameter("province");
                country = request.getParameter("country");
                postalCode = request.getParameter("postalCode");
                customerId = request.getParameter("customerId");
                description = request.getParameter("description");
                dateStarted = request.getParameter("dateStarted");
                dateFinished = request.getParameter("dateFinished");
                balance = request.getParameter("balance");
                jobStatus = request.getParameter("status");
            }

            String[] itemList = request.getParameterValues("itemList[]");
            String[] quantityList = request.getParameterValues("quantityList[]");
            String[] noteList = request.getParameterValues("noteList[]");
                
            String[] userList = request.getParameterValues("userList[]");
            String[] hoursList = request.getParameterValues("hoursList[]");

            String status = "";

                status = jobService.update(jobName, houseNumber, street, city, province, country, postalCode, customerId, description, dateStarted, dateFinished, balance, jobStatus);

                if (status != null && !status.contains("error") && !status.contains("exception")) {
                    if (itemList != null && itemList.length > 0) {
                        for (int i = 0; i < itemList.length; i++) {
                            String status2 = jobService.allocateItem(jobName, itemList[i], quantityList[i], noteList[i]);
                            if (status2.contains("error")) {
                                status = status2;
                            }
                        }
                    }
                    if (userList != null && userList.length > 0) {
                        for (int i = 0; i < userList.length; i++) {
                            String status2 = jobService.assignUser(jobName, userList[i], hoursList[i]);
                            if (status2.contains("error")) {
                                status = status2;
                            }
                        }
                    }
                }

                message = status;
//            }
//            else {
//                message = "You don't have privileges to update a Job.";
//            }
        } 
        
        job = jobService.getByJobName(jobName);
        if (job == null) {
            request.setAttribute("message", "Job not found. This seems like a database connection error.");
            getServletContext().getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
            return;
        }
        
        List<User> unasignedUserList = jobService.getUnasignedUserListFromJob(job);
        List<Item> unasignedItemList = jobService.getUnasignedItemListFromJob(job);
        ReportService reportService = new ReportService();
        List<Report> reportList = reportService.getByJobName(jobName);
        
        request.setAttribute("message", message);
        request.setAttribute("unasignedUserList", unasignedUserList);
        request.setAttribute("unasignedItemList", unasignedItemList);
        request.setAttribute("reportList", reportList);
        request.setAttribute("job", job);
        getServletContext().getRequestDispatcher("/WEB-INF/viewJob.jsp").forward(request, response);
    }
}
