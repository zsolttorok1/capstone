/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import utilities.CookieUtil;

/**
 *
 * @author 725899
 */
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String action = (String) request.getParameter("action");
            String status = request.getParameter("status");
            
            String message = null;
            HttpSession session = request.getSession();
            
            //cookie check: if found prefill form values
            String userName = CookieUtil.getCookieValue(request.getCookies(), "userName");
            if (userName != null && !userName.equals("")) {
                request.setAttribute("checked", "checked");
                request.setAttribute("userName", userName);
                request.setAttribute("message", message);
            }
            
            //session checks out, but not a logout attempt: if true then user is already logged in, redirect to homepage
            if (session.getAttribute("userName") != null && action == null) {
                response.sendRedirect("job");
                return;
            }
           
            //logout trigger check: if found kills current session
            if (action != null && action.equals("logout")) {
                message = "You have successfully logged out.";
                session.removeAttribute("userName");
                session.removeAttribute("role");
                session.invalidate();
                request.setAttribute("message", message);
            }
            
            //password reset trigger check
            if (action != null && action.equals("resetPassword")) {
                response.sendRedirect("resetPassword");
                return;
            }
            
            //status 0 = default
            //status 1 = OK: user's email found in database and email sent.
            //status 2 = Error: email not found in database.
            //status 3 = OK: password successfuly changed.
            //status 4 = Error: request expired

            if (status != null && status.equals("1"))
                request.setAttribute("message", "Password reset request link sent to the provided email address.");
            else if (status != null && status.equals("2"))
                request.setAttribute("message", "Error: provided email address not found in database.");
            else if (status != null && status.equals("3"))
                request.setAttribute("message", "Password successfuly changed! You may login with the new one.");
            else if (status != null && status.equals("4"))
                request.setAttribute("message", "Error: This password reset request is expired or never existed. You may make a new one.");
            
            //on any other cases: forward to loginpage please
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
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
            boolean rememberMe = false;
            String message = null;
            String userName = (String) request.getParameter("userName");
            String password = (String) request.getParameter("password");
            String rememberMeString = (String) request.getParameter("remember");
            if (rememberMeString != null) {
                rememberMe = Boolean.parseBoolean(rememberMeString);
            }
            
            if (userName != null && password != null
                && !userName.equals("") && !password.equals("")) {
                    UserService userService = new UserService();
                    String status = userService.login(userName, password);
                    
                    if (status == null || status.contains("error")) {
                        message = "error while attempting to login. Check database connection.";
                    }
                    else if (status.equals("invalid")) {
                        message = "Invalid username or password.";
                    }
                    //if login values are valid: do a session/cookie setup and redirect the user to home page
                    else {                                             
                        //set username session
                        HttpSession session = request.getSession();
                        session.setAttribute("userName", userName);
                        session.setAttribute("role", status);
                        session.setMaxInactiveInterval(60*60*24);  // expires after a day
                        
                        //set username cookie
                        Cookie usernameCookie = new Cookie("userName", userName);
                        
                        //"remember me" checkbox on: feed the browser with username cookie
                        if (rememberMe) {
                            usernameCookie.setMaxAge(60*60*24*365); // persistent for a year
                            usernameCookie.setPath("/");            // allowing access by entire app
                            response.addCookie(usernameCookie);
                        }
                        //"remember me" checkbox off: request to browser to forget username cookie
                        else {
                            if (usernameCookie != null) {
                                usernameCookie.setMaxAge(0); //effectively deleting the cookie, once it arrives
                                usernameCookie.setPath("/");
                                response.addCookie(usernameCookie);
                            }
                        }
                        
                        //routing the user to the privileged page                    
                        response.sendRedirect("job");
                        return;
                   }
            }
            else {
                message = "Both username and password fields must be filled.";
            }

            request.setAttribute("userName", userName);
            request.setAttribute("message", message);
            //response.sendRedirect("login");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
