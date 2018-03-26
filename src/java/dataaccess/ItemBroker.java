package dataaccess;

import database.ConnectionPool;
import domainmodel.Item;
import domainmodel.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemBroker {
    private static ItemBroker itemBroker;
    private ConnectionPool pool;
    private Connection connection;
    
    public static ItemBroker getInstance() {
        if (itemBroker == null) {
            itemBroker = new ItemBroker();
        }
        
        return itemBroker;
    }
    
    private ItemBroker() {
    }
    
    private void getConnection() {
        pool = ConnectionPool.getInstance();
        connection = pool.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //returns "Item, null"
    public Item getByName(String itemName) {
        getConnection();
        
        Item item = null;
        try {
            connection.setAutoCommit(false);
            
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT i.item_name, i.quantity, c.category_name, i.description"
                    + "     FROM item i" 
                    + "     JOIN `category` c ON c.category_id = i.category_id"
                    + "     WHERE i.item_name = ?;");
            pstmt.setString(1, itemName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String itemName2 = rs.getString("ITEM_NAME");
                int quantity = rs.getInt("QUANTITY");
                String category = rs.getString("CATEGORY_NAME");
                String description = rs.getString("DESCRIPTION");

                item = new Item(itemName2, quantity, category, description, null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return item;
    }
    
    public List<Item> search(String keyword) {
        getConnection();
//        String status = getConnection();
//        if (status == null || status.equals("connection error")) {
//            return null;
//        }
        
        List<Item> itemList = new ArrayList<>();
        Item item = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT i.item_name, i.quantity, c.category_name, i.description"
                    + "     FROM item i" 
                    + "     JOIN `category` c ON c.category_id = i.category_id"
                    + "     WHERE i.item_name like ?;");
                  
            pstmt.setString(1, "%" + keyword +"%");
            ResultSet rs = pstmt.executeQuery();
       
            while (rs.next()) {
                String itemName = rs.getString("ITEM_NAME");
                int quantity = rs.getInt("QUANTITY");
                String category = rs.getString("CATEGORY_NAME");
                String description = rs.getString("DESCRIPTION");

                item = new Item(itemName, quantity, category, description, null);
                itemList.add(item);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return itemList;
    }

    //returns "List of items, empty List, null"
    public List<Item> getAll() {
        getConnection();

        List<Item> itemList = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement(""
                    + "SELECT i.item_name, i.quantity, c.category_name, i.description" 
                    + "   FROM item i" 
                    + "   JOIN `category` c ON c.category_id = i.category_id;");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String itemName = rs.getString("ITEM_NAME");
                int quantity = rs.getInt("QUANTITY");
                String category = rs.getString("CATEGORY_NAME");
                String description = rs.getString("DESCRIPTION");

                Item item = new Item(itemName, quantity, category, description, null);
                itemList.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return itemList;
    }

    //returns "inserted, updated, error, exception"
    public String insert(Item item) {
        getConnection();
        
        String status = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement("select insert_item_func(?, ?, ?, ?)");
            pstmt.setString(1, item.getItemName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getDescription());

            ResultSet rs = pstmt.executeQuery();

            //get the status report from current database function
            while (rs.next()) {
                status = rs.getString(1);
            }
            
            //if something unexpected happened, rollback any changes.
            if (status == null || status.contains("error")) {
                connection.rollback();
                return "error";
            }
            //if all good, commit
            else {
                connection.commit();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
                return "exception";
            } catch (SQLException ex1) {
                Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return "exception";
        } finally {
            pool.freeConnection(connection);
        }

        return status;
    }

    //returns "updated, error, exception"
    public String update(Item item) {
        getConnection();
        
        String status = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement("select update_item_func(?, ?, ?, ?)");

            pstmt.setString(1, item.getItemName());    
            pstmt.setInt(2, item.getQuantity());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getDescription());

            ResultSet rs = pstmt.executeQuery();
            
            //get the status report from current database function
            while (rs.next()) {
                status = rs.getString(1);
            }
            
            //if something unexpected happened, rollback any changes.
            if (status == null || status.equals("error")) {
                connection.rollback();
                return "error";
            }
            //if all good, commit
            else {
                connection.commit();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex1);
                return "exception";
            }
            return "exception";
        } finally {
            pool.freeConnection(connection);
        }

        return status;
    }

    //returns "deleted, error, exception"
    public String delete(Item item) {
        getConnection();
        
        String status = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement("select delete_item_func(?)");
            pstmt.setString(1, item.getItemName());

            ResultSet rs = pstmt.executeQuery();
            
             //get the status report from current database function
            while (rs.next()) {
                status = rs.getString(1);
            }
            
            //if something unexpected happened, rollback any changes.
            if (status == null || status.equals("error")) {
                connection.rollback();
                return "error";
            }
            //if all good, commit
            else {
                connection.commit();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex1);
                return "exception";
            }
            return "exception";
        } finally {
            pool.freeConnection(connection);
        }

        return status;
    }
}
