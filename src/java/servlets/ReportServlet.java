/*Reference https://www.youtube.com/watch?v=OTV61E9xBTc
 *Reference https://pdfbox.apache.org/docs/2.0.8/javadocs/org/apache/pdfbox/pdmodel/PDPageContentStream.html
 *Reference https://www.tutorialspoint.com/pdfbox/pdfbox_quick_guide.htm
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.JobService;
import businesslogic.ReportService;
import businesslogic.UserService;
import domainmodel.Job;
import domainmodel.Report;
import domainmodel.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


/**
 *
 * @author 742227
 */
@WebServlet(name = "ReportServlet", urlPatterns = {"/ReportServlet"})
public class ReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        User user1 = new User();
        UserService ns1 = new UserService();
        user1 = ns1.viewUser(user);
        //doesnt allow users with out an account to get to this page
        //need to add only owner can see
//        if (session.getAttribute("user") == null || user1.getRole().equalsIgnoreCase("Manager") || user1.getRole().equalsIgnoreCase("Employee")) {
//            response.sendRedirect("login");
//
//            return;
//        }
        
        request.getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
        return;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String selectedReportName = request.getParameter("selectedUsername");
        if (action.equals("view")) {
            ReportService rs = new ReportService();
            Report report = new Report();
            //report = rs.getByName(selectedReportName);

            // request.setAttribute("reportName", report.getReportName());
            // request.setAttribute("description", report.getDescription());
            // request.setAttribute("dateCreated", report.getDateCreated());
            request.setAttribute("pdf", "");

        } else if (action.equals("generate")) {

            ReportService rs = new ReportService();
            Report report = new Report();
            // request.setAttribute("description", report.getDescription());
            // request.setAttribute("dateCreated", report.getDateCreated());
            // String pdfPath= report.getPDFFilePath());

            //make job object(by jobname)
            String selectedJobName = request.getParameter("JobName");
            //String pdfName = request.getParameter("pdfName");
            
            Job job = new Job();
            //job.setJobName(selectedJobName);
           
            PDDocument doc = new PDDocument(); // create pdf doc
            PDPage page1 = new PDPage();
            doc.addPage(page1); // adds page to pdf
            PDPageContentStream content = new PDPageContentStream(doc, page1);
            
            content.beginText();
            content.setFont(PDType1Font.HELVETICA , 27);
            //moves rge next line displayed to this spot
            content.newLineAtOffset(25, 740);
            
            //displays this line to screen
            content.showText("Job Name");
            //content.showText("Job Name: "+ job.getJobName());
            //close text
            content.endText();
            
            
            content.beginText();
            content.setFont(PDType1Font.HELVETICA , 27);
            content.newLineAtOffset(25, 710);
            content.showText("Customer Name");
            //content.showText("Customer Name: "+job.getCustomerName());
            content.endText();
            
            content.beginText();
            content.setFont(PDType1Font.HELVETICA , 20);
            content.newLineAtOffset(25, 660);
            content.showText("Date Started");
            //content.showText("Date Started: "+job.getDateStarted()+"");
            content.endText();
            
            content.beginText();
            content.setFont(PDType1Font.HELVETICA , 20);
            content.newLineAtOffset(25, 630);
            content.showText("Date Finished");
            //content.showText("Date Finished: "+job.getDateFinished()+"");
            content.endText();
            
            content.beginText();
            content.setFont(PDType1Font.HELVETICA , 20);
            content.newLineAtOffset(25, 510);
            content.showText("Job Description:");
            //content.showText("Job Descriotion: "+job.getDescription());
            content.endText();
            
            //make a soild line
            content.setLineWidth(0.5f);
            content.moveTo(25, 700);
            content.lineTo(570f, 700f);
            content.closeAndStroke();
            
            //Begin new text
            content.beginText();
            content.setFont(PDType1Font.HELVETICA , 20);
            content.newLineAtOffset(400, 660);
            content.showText("Job Status!!!!!!");
            //content.showText("Job Status: "+ job.getStatus());
            
            content.endText();
            
            content.beginText();
            content.setFont(PDType1Font.HELVETICA , 20);
            content.newLineAtOffset(400, 630);
            content.showText("Job Balance!!!!!!");
            //content.showText("Job Balance: "+ job.getBalance());
            content.endText();
            
            //close conetent Stream
            content.close();
           
            
            doc.save("c:/temp/report.pdf"); // saves repoprt filename
            
            doc.close(); //close file
            System.out.println("your file created in : " + System.getProperty("user.dir"));
        }
        
        request.setAttribute("errorMessage", "test report generated");
        request.getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
    }

}
