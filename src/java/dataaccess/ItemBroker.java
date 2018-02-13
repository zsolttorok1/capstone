package dataaccess;

import database.ConnectionPool;
import domainmodel.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemBroker {

    public Item getByName(String itemName) {
        // make prepared statement query, using itemName.
        // return result as Item 
        
        //i'm just faking it now:
        return new Item();
    }
    
    public List<Item> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        List<Item> itemList = new ArrayList<>();
        
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM item");
            //pstmt.setInt(2, 110592);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String itemName = rs.getString("ITEM_NAME");
                int quantity = rs.getInt("QUANTITY");
                String category = rs.getString("CATEGORY");
                String description = rs.getString("DESCRIPTION");
                
                Item item = new Item(itemName, quantity, category, description, null);
                itemList.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            pool.freeConnection(connection);
        }
                
        return itemList;
    }
    
    public boolean insert(Item item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
      
           try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO item(ITEM_NAME, QUANTITY, CATEGORY, DESCRIPTION) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, item.getItemName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getDescription());
            
            int updatedRows = pstmt.executeUpdate();
                
          } catch (SQLException ex) {
              Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
          }
          finally {
            pool.freeConnection(connection);
          }
        
        return true;
    }
    public boolean update(Item item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
                
        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE item SET "
                + "QUANTITY = ?, CATEGORY = ?, DESCRIPTION = ? "
                + "WHERE ITEM_NAME =?");
            pstmt.setInt(2, item.getQuantity());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getDescription());
            
            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            pool.freeConnection(connection);
        }
                
        return true;
    }
    
    public boolean delete(Item item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
                
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM item WHERE ITEM_NAME = ?");
            pstmt.setInt(2, item.getQuantity());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(2, item.getDescription());
            
            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            pool.freeConnection(connection);
        }
                
        return true;
    }
    

}
