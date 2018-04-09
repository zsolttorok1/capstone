/*Reference https://www.youtube.com/watch?v=OTV61E9xBTc
 *Reference https://pdfbox.apache.org/docs/2.0.8/javadocs/org/apache/pdfbox/pdmodel/PDPageContentStream.html
 *Reference https://www.tutorialspoint.com/pdfbox/pdfbox_quick_guide.htm
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.ReportService;
import businesslogic.UserService;
import dataaccess.CustomerBroker;
import dataaccess.ItemBroker;
import dataaccess.UserBroker;
import domainmodel.Item;
import domainmodel.Job;
import java.text.SimpleDateFormat;
import domainmodel.Report;
import domainmodel.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        user1 = ns1.getByUserName(user);

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
            
        }

        request.setAttribute("errorMessage", "test report generated");
        request.getRequestDispatcher("/WEB-INF/report.jsp").forward(request, response);
    }

}
