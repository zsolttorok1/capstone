package businesslogic;

import dataaccess.ItemBroker;
import domainmodel.Item;
import domainmodel.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemService {

    public String addItem(String itemName, String quantity, String category, String description, String note) {
        //check for nulls
        if (itemName == null || quantity == null || category == null || description == null
                || itemName.isEmpty() || quantity.isEmpty() || category.isEmpty() || description.isEmpty()) {
            return "error";
        }

        //check for values being valid
        int intQuantity = 0;
        //try catch this
        try {
            intQuantity = Integer.parseInt(quantity);

        } catch (NumberFormatException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        Item item = new Item(itemName, intQuantity, category, description, note);

        ItemBroker itemBroker = ItemBroker.getInstance();
        return itemBroker.insert(item);

    }

    public Item viewItem(String itemName) {
        ItemBroker itemBroker = ItemBroker.getInstance();

        Item item = itemBroker.getByName(itemName);

        return item;
    }

    public List<Item> searchItem(String keyword) {
        ItemBroker itemBroker = ItemBroker.getInstance();

        List<Item> itemList = null;
        
        if (!keyword.isEmpty()) {
            itemList = itemBroker.search(keyword);
        }
        else {
            itemList = itemBroker.getAll();
        }
        
        if (itemList == null)
            return null;
                
        return itemList;
    }
    //edit 
    public String edit(String itemName, String quantity, String category, String description, String note) {
        ItemBroker itemBroker = ItemBroker.getInstance();
        Item item = itemBroker.getByName(itemName);

        int intQuantity = 0;
        //try catch this
        try {
            intQuantity = Integer.parseInt(quantity);

        } catch (NumberFormatException ex) {
            Logger.getLogger(ItemBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        item.setQuantity(intQuantity);
        item.setCategory(category);
        item.setDescription(description);

        return itemBroker.update(item);
    }
    public String delete(String itemName) {
        ItemBroker itemBroker = ItemBroker.getInstance();
        Item deletedItem = itemBroker.getByName(itemName);
        return itemBroker.delete(deletedItem);
    }

    private int parseInteger(int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
