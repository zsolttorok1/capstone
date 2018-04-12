package domainmodel;

import java.io.Serializable;

/**
 * The java bean class that handles the Item object attributes and getter/setter methods. 
 * 
 */
public class Item implements Serializable {

    private String itemName;
    private int quantity;
    private int inventoryQuantity;
    private String category;
    private String description;
    private String note;

    /**
     * Constructor
     * 
     * @param itemName name of the item as a String
     * @param quantity quantity of the item as an Int
     * @param category category of the item as a String
     * @param description description of the item as a String
     * @param note note on the item regarding a job
     */
    public Item(String itemName, int quantity, String category, String description, String note) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
        this.note = "";
    }

    /**
     * Basic Constructor
     */
    public Item() {
    }

    /**
     * retrieves the item name
     *
     * @return item name as String
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * sets the item name
     *
     * @param itemName name of the item
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * retrieves the quantity of the item
     *
     * @return quantity as an Int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * sets the quantity
     *
     * @param quantity total quantity of the item as an Int
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * retrieves the category of the item
     *
     * @return category as a String
     */
    public String getCategory() {
        return category;
    }

    /**
     * sets the category of the item
     *
     * @param category category of the item as a String
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * retrieves the description of the item
     *
     * @return description as a String
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description of the item
     *
     * @param description description of the item as a String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * retrieves the note of the item regarding a job
     *
     * @return note as a String
     */
    public String getNote() {
        return note;
    }

    /**
     * sets the note of the item regarding a job
     *
     * @param note note on the item as a String
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * retrieves the remaining inventory of the item (which ones aren't being used on a job)
     *
     * @return quantity of unused items
     */
    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    /**
     * sets the remaining inventory of the item (which ones aren't being used on a job)
     *
     * @param inventoryQuantity remaining quantity of the item as an Int
     */
    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }
    
    
}
