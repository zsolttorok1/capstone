package domainmodel;

import java.io.Serializable;

public class Item implements Serializable {

    private String itemName;
    private int quantity;
    private String category;
    private String description;
    private String note;

    public Item(String itemName, int quantity, String category, String description, String note) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
        this.note = "";
    }

    public Item() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
