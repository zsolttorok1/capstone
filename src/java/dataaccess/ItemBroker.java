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
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Item item = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM item WHERE item_name = ?");
            pstmt.setString(1, itemName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String itemName2 = rs.getString("ITEM_NAME");
                int quantity = rs.getInt("QUANTITY");
                String category = rs.getString("CATEGORY_ID");
                String description = rs.getString("DESCRIPTION");

                item = new Item(itemName2, quantity, category, description, null);
            }
            pool.freeConnection(connection);

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }
        return item;

    }

    public List<Item> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        List<Item> itemList = new ArrayList<>();

        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT i.item_name, i.quantity, c.category_name, i.description "
                    + "FROM `item` i "
                    + "JOIN `category` c ON c.category_id = i.category_id;");

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

    public String insert(Item item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String result = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement("select insert_item_func(?, ?, ?, ?)");
            pstmt.setString(1, item.getItemName());
            pstmt.setInt(2, item.getQuantity());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getDescription());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result = rs.getString(1);
            }
            pool.freeConnection(connection);

            connection.commit();

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return result;
    }

    public String update(Item item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE item SET "
                    + "QUANTITY = ?, CATEGORY_ID = ?, DESCRIPTION = ? "
                    + "WHERE ITEM_NAME =?");
            pstmt.setInt(1, item.getQuantity());
            pstmt.setString(2, item.getCategory());
            pstmt.setString(3, item.getDescription());
            pstmt.setString(4, item.getItemName());

            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "updated";
    }

    public String delete(Item item) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM item WHERE ITEM_NAME = ?");
            pstmt.setString(1, item.getItemName());

            int rs = pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pool.freeConnection(connection);
        }

        return "deleted";
    }

}
