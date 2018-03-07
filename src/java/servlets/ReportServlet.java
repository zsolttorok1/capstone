/*Reference https://www.youtube.com/watch?v=OTV61E9xBTc
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
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;

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
        if (session.getAttribute("user") == null || user1.getRole().equalsIgnoreCase("Manager") || user1.getRole().equalsIgnoreCase("Employee")) {
            response.sendRedirect("login");

            return;
        }
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
            JobService jobService = new JobService();
            Job job = new Job();
            /*                File file = new File("res/reports");
                PrintWriter out
                        = new PrintWriter(
                                new FileWriter(file));
                
                 
                    out.println();
                

                out.close();
           
             */

            PDDocument doc = new PDDocument(); // create pdf doc
            PDPage page1 = new PDPage();
            doc.addPage(page1); // adds page to pdf
            PDPageContentStream content = new PDPageContentStream(doc, page1);
            
            content.beginText();
            content.showText(job.getCustomerName());
            content.setFont(PDType1Font.HELVETICA , 27);
            content.moveTo(260, 750);
            
            //content.drawIamge();
            content.endText();
            doc.save("filename"); // saves repoprt filename
            doc.close(); //close file
        }
    }
}
