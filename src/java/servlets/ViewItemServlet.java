/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.ItemService;
import businesslogic.UserService;
import domainmodel.Item;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 685442
 */
public class ViewItemServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ItemService itemService = new ItemService();

        List<Item> itemList = itemService.searchItem("");
        if (itemList == null) {
            request.setAttribute("message", "Item not found. This seems like a database connection error.");
        }

        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String itemName = request.getParameter("itemName");
        
        if (itemName == null || itemName.isEmpty()) {
            request.setAttribute("message", "invalid itemName.");
            getServletContext().getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
            return;
        }
        
        if (action != null && action.equals("view")) {
  
            ItemService itemService = new ItemService();
            Item item = new Item();
            item = itemService.getByItemName(itemName);

            if (item == null) {
                request.setAttribute("message", "Item not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
                return;
            }

            request.setAttribute("item", item);

            request.getRequestDispatcher("/WEB-INF/viewItem.jsp").forward(request, response);
            return;
        }
        else if (action != null && action.equals("save")) {
            String quantity = request.getParameter("quantity");
            String category = request.getParameter("category");
            String description = request.getParameter("description");
                        
            String status = "";
            
            ItemService itemService = new ItemService();
            
            status = itemService.update(itemName, quantity, category, description);
            
            request.setAttribute("message", status);
            
            Item item = itemService.getByItemName(itemName);
            if (item == null) {
                request.setAttribute("message", "Item not found. This seems like a database connection error.");
                getServletContext().getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
                return;
            }

            request.setAttribute("item", item);
            getServletContext().getRequestDispatcher("/WEB-INF/viewItem.jsp").forward(request, response);
        }
    }
}
