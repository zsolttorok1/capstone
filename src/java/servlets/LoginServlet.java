package servlets;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import businesslogic.UserService;

import domainmodel.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 742227
 */
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute("errorMessage", "Logged out");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        // stop other execution of code

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //Start of login code
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        
        // validation
        if (userName == null || userName.isEmpty() || passWord == null || passWord.isEmpty()) {
            // set error message

            request.setAttribute("errorMessage", "Both values are required");

            // forward the request back to login page.jsp
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            // stop other execution of code
            return;
        }
                //makes a user
        User user = new User();

        user.setUsername(userName);
        user.setPassword(passWord);

        //Checks if user object has a username and password and if null then
        if (user.getUsername() == null || user.getUsername().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()) {
            // set error message
            request.setAttribute("errorMessage", "Both values are required");

            // forward the request back to index.jsp
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            // stop other execution of code
            return;
        }
        
    }
}
