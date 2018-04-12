package businesslogic;

import dataaccess.ItemBroker;
import domainmodel.Item;
import java.util.List;

/**
 * Item Service handles all the operations regarding the Item Object, including inserting, deleting, updating
 * and building.
 * 
 */
public class ItemService {
    
    /**
     * Insert calls the ItemBroker class in order to insert a Item Object into the database.
     *
     * @param itemName name of the item as a String
     * @param quantity amount of the item  as a String
     * @param categorycategory of the item as a String
     * @param description  description of the item as a String
     * @return status of insertion in database
     */
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

    /**
     * Retrieves item Object (if found) by its unique name from the Broker.
     *
     * @param itemName name of the item to be retrieved
     * @return item Object
     */
    public Item getByItemName(String itemName) {
        
        if (itemName != null && !itemName.isEmpty()) {
            ItemBroker itemBroker = ItemBroker.getInstance();
            return itemBroker.getByName(itemName);
        }
        else {
            return null;
        }
    }
    
    /**
     * Retrieves all the items from the database using the itemBroker.
     *
     * @return list of all item Objects in database
     */
    public List<Item> getAll() {
        ItemBroker itemBroker = ItemBroker.getInstance();
        return itemBroker.getAll();
    }
    
    /**
     * Searches through all existing items for matching keyword using itemBroker.
     *
     * @param keyword search term entered
     * @return list of item Objects which match keyword
     */
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

    /**
     * Updates existing item Object by setting its attributes to new parameters
     *
     * @param itemName name of the item as a String
     * @param quantity amount of the item  as a String
     * @param categorycategory of the item as a String
     * @param description  description of the item as a String
     * @return updated item Object
     */
    public String update(String itemName, String quantity, String category, String description) {
        Item item = build(itemName, quantity, category, description);
        
        return update(item);
    }
    
    /**
     * Updates existing item Object to match temporary new item Object
     *
     * @param itemNew the new item Object
     * @return status of update from database
     */
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
      
    /**
     * Makes sure none of the parameters in Item object are null.
     *
     * @param item item object to be examined
     * @return status of validation check
     */
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
    
    /**
     * Builds Item object using parameters and validates data.
     *
     * @param itemName name of the item as a String
     * @param quantity amount of the item  as a String
     * @param categorycategory of the item as a String
     * @param description  description of the item as a String
     * @return built item Object
     */
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
    
    /**
     * Deletes an existing Item object from database based on its unique item name.
     *
     * @param itemName unique item name as a String
     * @return
     */
    public String delete(String itemName) {
        ItemBroker itemBroker = ItemBroker.getInstance();
        Item deletedItem = itemBroker.getByName(itemName);
        
        return itemBroker.delete(deletedItem);
    }
}