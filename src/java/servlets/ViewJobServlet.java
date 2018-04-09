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
        String message = "";

        List<Job> jobList = jobService.searchJob("");
        if (jobList == null) {
            message = "Job not found. This seems like a database connection error.";
        }
        
//        request.setAttribute("jobList", jobList);
//        request.getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);

        //testing purposes:
        String jobName = "Brookfield Bathroom on WestTower";
        Job job = jobService.getByJobName(jobName);
        
        if (job == null ) {
            message = "Job not found. This seems like a database connection error.";
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/WEB-INF/job.jsp").forward(request, response);
            return;
        }
        
        List<User> unasignedUserList = jobService.getUnasignedUserListFromJob(job);
        List<Item> unasignedItemList = jobService.getUnasignedItemListFromJob(job);
        ReportService reportService = new ReportService();
        List<Report> reportList = reportService.getByJobName(jobName);
        
        request.setAttribute("job", job);
        request.setAttribute("message", message);
        request.setAttribute("unasignedUserList", unasignedUserList);
        request.setAttribute("unasignedItemList", unasignedItemList);
        request.setAttribute("reportList", reportList);
        request.getRequestDispatcher("/WEB-INF/viewJob.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
            String userName = request.getParameter("assignedUserName");
            String hours = request.getParameter("hours");
            
            String status = jobService.assignUser(jobName, userName, hours);
            message = status;
        }
        else if (unassignedUserName != null) {
            String status = jobService.unassignUser(jobName, unassignedUserName);
            message = status;
        }
        else if (generate != null) {
            String reportDescription = request.getParameter("reportDescription");
            String status = "";
            
            ReportService reportService = new ReportService();
            status = reportService.generateFromJob(job, reportDescription);
            message = status;
        }
        else if (reportId != null){
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
        else if (save != null) {
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
            String jobStatus = request.getParameter("status");
            
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
