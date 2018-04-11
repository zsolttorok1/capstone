/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainmodel;

/**
 *
 * @author 742227
 */
public class Quote {

    private int quoteId;
    private String name;
    private String email;
    private String description;

    public Quote(int quoteId, String name, String email, String description) {
        this.quoteId = quoteId;
        this.name = name;
        this.email = email;
        this.description = description;
    }
    
    public Quote(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public Quote() {
        this.quoteId = 0;
        this.name = null;
        this.email = null;
        this.description = null;
    }

    public int getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
