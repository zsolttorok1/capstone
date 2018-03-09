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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 685442
 */
public class ViewUserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String selectedUserName = request.getParameter("selectedUsername");
        
        String errorMessage = "";
        
        if (action != null && action.equals("view")) {
            if (selectedUserName != null && !selectedUserName.equals("")) {
                
                UserService is = new UserService();
                User user = new User();
                user = is.viewUser(selectedUserName);
                
                request.setAttribute("userName", user.getUserName());
                request.setAttribute("firstname", user.getFirstName());
                request.setAttribute("lastname", user.getLastName());
                request.setAttribute("houseNo", user.getHouseNumber());
                request.setAttribute("address", user.getStreet());
                request.setAttribute("email", user.getEmail());
                
                
                request.getRequestDispatcher("/WEB-INF/viewUser.jsp").forward(request, response);
                return;
            }
            
            else {
                request.setAttribute("errorMessage", "didnt work");
            }
        }
        else if (action != null && action.equals("save")) {
                
            
            String itemName = request.getParameter("name");
            String quantity = request.getParameter("quantityEdit");
            String category = request.getParameter("categoryEdit");
            String description = request.getParameter("descriptionEdit");
            String note = null;
            
            if (itemName != null && !itemName.isEmpty() && quantity != null && !quantity.isEmpty() && category != null && !category.isEmpty() && description != null && !description.isEmpty() && quantity.matches("\\d+")) {
                ItemService itemService = new ItemService();
                itemService.edit(itemName, quantity, category, description, note);
                errorMessage = "Item Successfully Changed";
                
                Item item = new Item();
                item = itemService.viewItem(itemName);
                
                request.setAttribute("itemName", item.getItemName());
                request.setAttribute("description", item.getDescription());
                request.setAttribute("category", item.getCategory());
                request.setAttribute("quantity", item.getQuantity());
                if (item.getNote() != null && !item.getNote().equals("")){
                    request.setAttribute("notes", item.getNote());
                }
                else {
                    request.setAttribute("notes", "No Additional Notes Available.");
                }
                
                getServletContext().getRequestDispatcher("/WEB-INF/viewItem.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("errorMessage", "The following fields need to be entered: Item Name, quantity, category and description");
                
                ItemService itemService = new ItemService();
                itemService.edit(itemName, quantity, category, description, note);
                errorMessage = "Item Successfully Changed";
                
                Item item = new Item();
                item = itemService.viewItem(selectedUserName);
                
                request.setAttribute("itemName", item.getItemName());
                request.setAttribute("description", item.getDescription());
                request.setAttribute("category", item.getCategory());
                request.setAttribute("quantity", item.getQuantity());
                if (item.getNote() != null && !item.getNote().equals("")){
                    request.setAttribute("notes", item.getNote());
                }
                else {
                    request.setAttribute("notes", "No Additional Notes Available.");
                }
                
                getServletContext().getRequestDispatcher("/WEB-INF/viewItem.jsp").forward(request, response);
                return;
            }
        }
        else {
            request.setAttribute("errorMessage", "outside didnt work");
        }
        
        HttpSession session = request.getSession();
        ItemService itemService = new ItemService();

        //logic
        String keyword = "anything";
        List<Item> itemList = itemService.searchItem(keyword);

        session.setAttribute("itemList", itemList);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/WEB-INF/viewItem.jsp").forward(request, response);
    }

}
