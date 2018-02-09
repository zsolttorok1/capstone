package businesslogic;

import dataaccess.ItemBroker;
import domainmodel.Item;
import java.util.List;

public class ItemService { 
    
    public boolean addItem(String itemName, String quantity, String category, String description, String note) {
        //check for nulls
        if (itemName == null || quantity == null || category == null || description == null) {
            return false;
        }
        
        //check for values being valid
        
        //try catch this
        int intQuantity = Integer.parseInt(quantity);
   
        Item item = new Item(itemName, intQuantity, category, description, note);
        
        ItemBroker itemBroker = new ItemBroker();
        return itemBroker.insert(item);
    }
    
    public Item viewItem(String itemName) {
        ItemBroker itemBroker = new ItemBroker();
        
        Item item = itemBroker.getByName(itemName);
        
        return item;
    }
        
    public List<Item> searchItem(String keyword) {
        ItemBroker itemBroker = new ItemBroker();
        
        //this always return all items for now
        
        return itemBroker.getAll();
    }
}