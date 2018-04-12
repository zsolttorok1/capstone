package servlets;

import businesslogic.QuoteService;
import domainmodel.Quote;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "QuoteServlet", urlPatterns = {"/QuoteServlet"})
public class QuoteServlet extends HttpServlet {
    
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
        if (session.getAttribute("userName") == null || !session.getAttribute("role").equals("owner")) {
            response.sendRedirect("login");
            return;
        }
        
        QuoteService quoteService = new QuoteService();

        List<Quote> quoteList = quoteService.searchQuote("");
        if (quoteList == null) {
            request.setAttribute("message", "Quote not found. This seems like a database connection error.");
        }

        request.setAttribute("quoteList", quoteList);
        request.getRequestDispatcher("/WEB-INF/quote.jsp").forward(request, response);
      
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
        if (session.getAttribute("userName") == null || !session.getAttribute("role").equals("owner")) {
            response.sendRedirect("login");
            return;
        }
        
        String action = request.getParameter("action");
        String quoteId = request.getParameter("quoteId");
        String message = "";
        
        if (quoteId == null || quoteId.isEmpty()) {
            message = "invalid quoteId.";
        }
        else if (action != null && action.equals("view")) {
            QuoteService quoteService = new QuoteService();
            Quote quote = quoteService.viewQuote(quoteId);
                    
            if (quote == null) {
                request.setAttribute("message", "Quote not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/quote.jsp").forward(request, response);
                return;
            }
        
            request.setAttribute("quote", quote);
            request.getRequestDispatcher("/WEB-INF/viewQuote.jsp").forward(request, response);
            return;
        }        
        else if (action != null && action.equals("delete")) {
            QuoteService quoteService = new QuoteService();
            String status = quoteService.delete(quoteId);
         
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