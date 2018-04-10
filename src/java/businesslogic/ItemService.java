package businesslogic;

import dataaccess.ItemBroker;
import domainmodel.Item;
import java.util.List;

public class ItemService {
    
    public String insert(String itemName, String quantity, String category, String description) {
        
        Item item = build(itemName, quantity, category, description);
        
        String statusOutput = validate(item);
        
        if (statusOutput != null && statusOutput.equals("ok")) {
            ItemBroker itemBroker = ItemBroker.getInstance();
            statusOutput = itemBroker.insert(item);
        }
        
        if (statusOutput != null && statusOutput.contains("inserted")) {
            statusOutput = "Item Successfully Added";
        }
        else if (statusOutput != null && statusOutput.contains("updated")) {
            statusOutput = "Item Successfully Updated";
        }

        return statusOutput; 
    }

    public Item getByItemName(String itemName) {
        
        if (itemName != null && !itemName.isEmpty()) {
            ItemBroker itemBroker = ItemBroker.getInstance();
            return itemBroker.getByName(itemName);
        }
        else {
            return null;
        }
    }
    
    public List<Item> getAll() {
        ItemBroker itemBroker = ItemBroker.getInstance();
        return itemBroker.getAll();
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

    public String update(String itemName, String quantity, String category, String description) {
        Item item = build(itemName, quantity, category, description);
        
        return update(item);
    }
    
    public String update(Item itemNew) {
        ItemBroker itemBroker = ItemBroker.getInstance();
        Item item = null;
        String status = "";
        
        if (itemNew.getItemName() != null) 
            item = itemBroker.getByName(itemNew.getItemName());
        else 
            return "invalid itemName";
        
        if (item == null)
            return "item name not found while attempting to update. Check database connection.";
        
        //prepare changed attributes on the updatable Item
        if (itemNew.getQuantity() > 0 )
            item.setQuantity(itemNew.getQuantity());
        if (itemNew.getCategory() != null && !itemNew.getCategory().isEmpty() )
            item.setCategory(itemNew.getCategory());
        if (itemNew.getDescription() != null && !itemNew.getDescription().isEmpty() )
            item.setDescription(itemNew.getDescription());
       
        //make sure that the new attribute values are valid, before updating.
        status = validate(item);
        
        if (status != null && status.equals("ok")) {
            status = itemBroker.update(item);
                    
            if (status != null && status.contains("inserted")) {
                status = "Item Successfully Added";
            }
            else if (status != null && status.contains("updated")) {
                status = "Item Successfully Updated";
            }
        }
        
        return status;
    }
      
    private String validate(Item item) {
        String status = "";
        
        if (item == null || item.getItemName() == null || item.getItemName().isEmpty()) {
            status += "invalid itemName ";
        }
        if (item.getQuantity() < 0) {
            status += "invalid quantityNumber ";
        }
        if (item.getCategory() == null || item.getCategory().isEmpty()) {
            status += "invalid category ";
        }
        if (item.getDescription() == null || item.getDescription().isEmpty()) {
            status += "invalid description ";
        }
        
        if (status.isEmpty()) {
            return "ok";
        }
        else { 
            return status;
        }
    }
    
    private Item build(String itemName, String quantity, String category, String description) {
        Item item = new Item();
       
        if (itemName != null && !itemName.isEmpty()) {
            item.setItemName(itemName);
        }
        if (quantity != null && !quantity.isEmpty()) {
            try {
                int intQuantity = Integer.parseInt(quantity);
                item.setQuantity(intQuantity);
            } catch (NumberFormatException ex) {
                item.setQuantity(-1);
            }
        }
        if (category != null && !category.isEmpty()) {
            item.setCategory(category);
        }
        if (description != null && !description.isEmpty()) {
            item.setDescription(description);
        }
            
        return item;
    }
    
    public String delete(String itemName) {
        ItemBroker itemBroker = ItemBroker.getInstance();
        Item deletedItem = itemBroker.getByName(itemName);
        
        return itemBroker.delete(deletedItem);
    }
}