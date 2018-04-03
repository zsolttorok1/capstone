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

        List<Quote> quoteList = quoteService.searchQuote("");
        if (quoteList == null) {
            request.setAttribute("message", "Quote not found. This seems like a database connection error.");
        }

        request.setAttribute("quoteList", quoteList);
        request.getRequestDispatcher("/WEB-INF/quote.jsp").forward(request, response);
      
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String quoteName = request.getParameter("quoteName");
        String message = "";
        
        if (quoteName == null || quoteName.isEmpty()) {
            message = "invalid quoteId.";
        }
        else if (action != null && action.equals("view")) {
            QuoteService quoteService = new QuoteService();
            Quote quote = quoteService.viewQuote(quoteName);
                    
            if (quote == null) {
                request.setAttribute("message", "Customer not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/quote.jsp").forward(request, response);
                return;
            }
        
            request.setAttribute("quote", quote);
            request.getRequestDispatcher("/WEB-INF/viewQuote.jsp").forward(request, response);
            return;
        }        
        else if (action != null && action.equals("delete")) {
            QuoteService quoteService = new QuoteService();
            String status = quoteService.delete(quoteName);
         
            message = status;
        }
        
        QuoteService quoteService = new QuoteService();

        List<Quote> quoteList = quoteService.searchQuote("");
        if (quoteList == null) {
            message =  "Quote not found. This seems like a database connection error.";
        }

        request.setAttribute("message", message);
        request.setAttribute("quoteList", quoteList);
        request.getRequestDispatcher("/WEB-INF/quote.jsp").forward(request, response);
    }
}