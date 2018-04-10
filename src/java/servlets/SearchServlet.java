/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.CustomerService;
import businesslogic.ItemService;
import businesslogic.JobService;
import businesslogic.SearchService;
import businesslogic.UserService;
import domainmodel.Customer;
import domainmodel.Item;
import domainmodel.Job;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 742227
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
