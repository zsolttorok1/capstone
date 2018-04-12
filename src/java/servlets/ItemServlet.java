package servlets;

import businesslogic.ItemService;
import domainmodel.Item;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ItemServlet extends HttpServlet {

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
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;
        }
        
        ItemService itemService = new ItemService();
        List<Item> itemList = itemService.searchItem("");
        if (itemList == null) {
            request.setAttribute("message", "No items found. This seems like a database connection error.");
        }
        
        request.setAttribute("itemList", itemList);

        request.getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
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
        //access privilege check
        HttpSession session = request.getSession();   
        if (session.getAttribute("userName") == null) {
            response.sendRedirect("login");
            return;
        }
        
        ItemService itemService = new ItemService();
        
        String message = "";
        String action = request.getParameter("action");
        String itemName = request.getParameter("itemName");
     
        if (action.equals("delete")) {
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
                String status = itemService.delete(itemName);
                message = status;
            }
            else {
                message = "You don't have privileges to delete an Item.";
            }
        } else if (action.equals("add")) {
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
                String quantity = request.getParameter("quantity");
                String category = request.getParameter("category");
                String description = request.getParameter("description");
                String status = itemService.insert(itemName, quantity, category, description);

                message = status;
            }
            else {
                message = "You don't have privileges to add an Item.";
            }
        }

        List<Item> itemList = itemService.searchItem("");
        if (itemList == null) {
            message = "Item not found. This seems like a database connection error.";
        }

        request.setAttribute("message", message);
        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
    }
}
