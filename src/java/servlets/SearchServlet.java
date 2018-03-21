/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.CustomerService;
import businesslogic.ItemService;
import businesslogic.JobService;
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
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        //doesnt allow users with out an account to get to this page
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");

            return;
        }
        User user1 = new User();
        //TODO PULL FROM SESSION

        UserService ns1 = new UserService();
        try {
            user1 = ns1.getByUserName(user);
            request.setAttribute("user", user1);
        } catch (Exception ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user1.getRole().equalsIgnoreCase("Owner")) {
            // response.sendRedirect("login");
            //return;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String user = (String) session.getAttribute("user");
        //doesnt allow users with out an account to get to this page
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login");

            return;
        }
        if (action.equals("search")) {
            ArrayList<Object> hits = new ArrayList();
            User user1 = new User();
            String keyword = request.getParameter("keyword");
            UserService ns1 = new UserService();
            
            //finds user requesting search
            try {
                user1 = ns1.getByUserName(user);
            } catch (Exception ex) {
                Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //add items to search
            ItemService itemService = new ItemService();
            List<Item> itemList = itemService.searchItem(keyword);
            hits.add(itemList);
            
            //grab jobs to add to array of keyword list
            JobService js = new JobService();
            List<Job> jobList = js.searchJob(keyword);
            
            
            //add more search items that only the Owner can see
            if (user1.getRole().equalsIgnoreCase("Owner")) {

                //adds users to keyword search
                List<User> users = new ArrayList();
                users = ns1.searchUser(keyword);
                hits.add(users);
                
                //add all jobs
                hits.add(jobList);
                
                //add reports
                //ReportService rs = new ReportService();
                //List<Reports> reportList = rs.searchReport(keyword);
                //hits.add(reportList);
                
                //add Customer
                CustomerService cs = new CustomerService();
                List<Customer> customerList = cs.searchCustomer(keyword);
                hits.add(customerList);
                
                request.setAttribute("search", hits);
                return;

            } //adds only what the manager and employee can see
            else {
                //adds users to keyword search
                hits.add(user1);
                
                //add jobs only the user is associated to
                jobList= js.getByUser(user1.getUserName());
                hits.add(jobList);
                
                request.setAttribute("search", hits);
                return;
            }
        }
    }
}
