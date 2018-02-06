package businesslogic;

import dataaccess.ItemBroker;
import domainmodel.Item;
import java.util.List;

public class ItemService { 
      
    public boolean addItem(Item item) {
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