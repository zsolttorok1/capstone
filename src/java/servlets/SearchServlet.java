package servlets;

import businesslogic.SearchService;
import domainmodel.Customer;
import domainmodel.Item;
import domainmodel.Job;
import domainmodel.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;
        }
        
        String keyword = request.getParameter("keyword");
        
        SearchService searchService = new SearchService();
        
        List<User> userList = searchService.searchUser(keyword);
        List<Item> itemList = searchService.searchItem(keyword);
        List<Customer> customerList = searchService.searchCustomer(keyword);
        List<Job> jobList = searchService.searchJob(keyword);
        
        request.setAttribute("userList", userList);
        request.setAttribute("itemList", itemList);
        request.setAttribute("customerList", customerList);
        request.setAttribute("jobList", jobList);
        request.setAttribute("keyword", keyword);
        
        request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
    }
}
