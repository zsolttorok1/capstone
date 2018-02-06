package dataaccess;

import domainmodel.Item;
import java.util.ArrayList;
import java.util.List;

public class ItemBroker {

    public Item getByName(String itemName) {
        // make prepared statement query, using itemName.
        // return result as Item 
        
        //i'm just faking it now:
        return new Item();
    }
    
    public List<Item> getAll() {
        // make prepared statement query.
        // return result as ItemArray 
        
        //i'm just faking it now:
        List<Item> itemList = new ArrayList<>();
        
        return itemList;
    }
    
    public boolean insert(Item item) {
        // make prepared statement query.
        // return True boolean if insert was successful
        
        //i'm just faking it now:        
        return true;
    }
}
