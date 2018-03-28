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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ItemServlet", urlPatterns = {"/ItemServlet"})
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
        ItemService itemService = new ItemService();

        List<Item> itemList = itemService.searchItem("");

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
        //Variables
        String action = request.getParameter("action");
        String selectedItemName = request.getParameter("selectedItemName");
        
        String errorMessage = "";

        //Adding an Item 
        if (action != null && action.equals("add")) {
            String itemName = request.getParameter("name");
            String quantity = request.getParameter("quantity");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            String note = null;

            if (itemName != null && !itemName.isEmpty() && quantity != null && !quantity.isEmpty() && category != null && !category.isEmpty() && description != null && !description.isEmpty() && quantity.matches("\\d+")) {
                ItemService itemService = new ItemService();
                itemService.addItem(itemName, quantity, category, description, note);
                errorMessage = "Item Successfully added";
            } else {
                request.setAttribute("errorMessage", "The following fields need to be entered: Item Name, quantity, category and description");
                getServletContext().getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
                return;
            }
        } else if (action != null && action.equals("delete")) {
            if (selectedItemName != null) {
                ItemService itemService = new ItemService();
                itemService.delete(selectedItemName);
                errorMessage = "You deleted an item.";
                //request.setAttribute("errorMessage", "You deleted an item.");

            } else {
                request.setAttribute("errorMessage", "You do not have the authority to DELETE me.");
                getServletContext().getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
                return;
            }
        } else if (action != null && action.equals("view")) {
            getServletContext().getRequestDispatcher("/WEB-INF/viewItem.jsp").forward(request, response);
            return;
            /*
            String itemName = request.getParameter("name");
            String quantity = request.getParameter("quantity");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            String note = null;
            */
        }

        ItemService itemService = new ItemService();
        List<Item> itemList = itemService.searchItem("");

        request.setAttribute("itemList", itemList);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
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
