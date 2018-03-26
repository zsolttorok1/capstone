/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.QuoteService;
import domainmodel.Quote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "QuoteServlet", urlPatterns = {"/QuoteServlet"})
public class QuoteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                QuoteService quoteService = new QuoteService();

        String keyword = "anything";
        List<Quote> quoteList = quoteService.searchQuote(keyword);
        if (quoteList == null) {
            request.setAttribute("message", "Quote not found. This seems like a database connection error.");
        }

        request.setAttribute("quoteList", quoteList);
        request.getRequestDispatcher("/WEB-INF/viewQuote.jsp").forward(request, response);
      
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //Variables
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String description = request.getParameter("description");
        
        
        String errorMessage = "";

        //Adding an Quote
        if (action != null && action.equals("submit")) {
              if (name != null && email != null && description !=null) {
                
                    errorMessage = "You Submitted a Quote.";
                

            } else {
                request.setAttribute("errorMessage", "You need to enter something in all fields.");
                getServletContext().getRequestDispatcher("/WEB-INF/submitQuote.jsp").forward(request, response);
                return;
            }
        
        
        }
    
    
    }
}