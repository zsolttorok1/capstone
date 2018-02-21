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
     * @param request servlet req```1uest
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //instanciating all used services
        HttpSession session = request.getSession();
        ItemService itemService = new ItemService();

        //logic
        String keyword = "anything";
        List<Item> itemList = itemService.searchItem(keyword);

        //saving attributes to session
        session.setAttribute("itemList", itemList);

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
            } else {
                request.setAttribute("errorMessage", "The following fields need to be entered: Item Name, quantity, category and description");
                getServletContext().getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("delete")) {
            if (selectedItemName != null) {
                ItemService itemService = new ItemService();
                itemService.delete(selectedItemName);
                request.setAttribute("errorMessage", "You Deleted a item");
                return;
            } else {
                request.setAttribute("errorMessage", "You can not delete ");
                getServletContext().getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
                return;
            }
        } else if (action.equals("edit")) {
            String itemName = request.getParameter("name");
            String quantity = request.getParameter("quantity");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
            String note = null;
            
            if (itemName != null && !itemName.isEmpty() && quantity != null && !quantity.isEmpty() && category != null && !category.isEmpty() && description != null && !description.isEmpty() && quantity.matches("\\d+")) {
                ItemService itemService = new ItemService();
                itemService.edit(itemName, quantity, category, description,note);
                request.setAttribute("errorMessage", "You Edited your Item");
            } else {
                request.setAttribute("errorMessage", "You can not edit");
                getServletContext().getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
                return;

            }
        }

        HttpSession session = request.getSession();
        ItemService itemService = new ItemService();

        //logic
        String keyword = "anything";
        List<Item> itemList = itemService.searchItem(keyword);

        session.setAttribute("itemList", itemList);
        request.setAttribute("errorMessage", "Item Successfully added");
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
