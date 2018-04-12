package domainmodel;

/**
 * The java bean class that handles the Quote object attributes and getter/setter methods. 
 */
public class Quote {

    /**
     * contains the unique ID of the Quote
     */
    private int quoteId;
    
    /**
     * contains the name of the person who made the Quote
     */
    private String name;
    
    /**
     * contains the email address of the person who made the Quote
     */
    private String email;
    
    /**
     * contains the body of the Quote
     */
    private String description;

    /**
     * Constuctor
     * @param quoteId contains the unique ID of the Quote
     * @param name contains the name of the person who made the Quote
     * @param email contains the email address of the person who made the Quote
     * @param description contains the body of the Quote
     */
    public Quote(int quoteId, String name, String email, String description) {
        this.quoteId = quoteId;
        this.name = name;
        this.email = email;
        this.description = description;
    }
    
    /**
     * Constuctor 2
     * @param name contains the name of the person who made the Quote
     * @param email contains the email address of the person who made the Quote
     * @param description contains the body of the Quote
     */
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

    /**
     * gets the quoteId
     * @return the quoteId
     */
    public int getQuoteId() {
        return quoteId;
    }

    /**
     * sets the quoteId
     * @param quoteId the quoteId
     */
    public void setQuoteId(int quoteId) {
        this.quoteId = quoteId;
    }
    
    /**
     * gets the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the email
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the description
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
