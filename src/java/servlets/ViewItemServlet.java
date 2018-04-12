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

public class ViewItemServlet extends HttpServlet {
    
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
            request.setAttribute("message", "Item not found. This seems like a database connection error.");
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
            if (session.getAttribute("role").equals("owner") || session.getAttribute("role").equals("manager")) {
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
            else {
                request.setAttribute("message", "You don't have privileges to update an Item.");
                getServletContext().getRequestDispatcher("/WEB-INF/item.jsp").forward(request, response);
            }
        }
    }
}
