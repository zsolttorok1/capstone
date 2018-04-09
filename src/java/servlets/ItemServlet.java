/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.ItemService;
import domainmodel.Item;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 742227
 */
public class ItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ItemService itemService = new ItemService();
        List<Item> itemList = itemService.searchItem("");
        if (itemList == null) {
            request.setAttribute("message", "No users found. This seems like a database connection error.");
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
        ItemService itemService = new ItemService();
        
        String message = "";
        String action = request.getParameter("action");
        String itemName = request.getParameter("itemName");
     
        if (action.equals("delete")) {
            String status = itemService.delete(itemName);
            message = status;
        } else if (action.equals("add")) {
            String quantity = request.getParameter("quantity");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            String status = itemService.insert(itemName, quantity, category, description);
            
            message = status;
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
