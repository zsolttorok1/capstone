/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;
import dataaccess.QuoteBroker;
import domainmodel.Quote;
import java.util.List;

/**
 * Quote Service handles all the operations regarding the Quote Object, including adding, viewing, deleting
 * and searching.
 * 
 */
public class QuoteService {

    /**
     * Inserts new Quote Object into database using the QuoteBroker.
     *
     * @param name name of quote as a String
     * @param description description(information) of quote as a String
     * @param email email address of potential customer writing the quote as a String
     * @return status of quote insertion
     */
    public String addQuote(String name, String description, String email) {
        //check for nulls
        if (name == null || description == null || email == null || name.isEmpty()
                || description.isEmpty() || email.isEmpty()) {
            
            return "error";
        }

        Quote quote = new Quote(name, description, email);

        QuoteBroker quoteBroker = new QuoteBroker();
        return quoteBroker.insert(quote);
    }

    /**
     * Loads all data of a Quote Object matching unique quote ID to view
     *
     * @param quoteId unique quote ID as a String
     * @return quote Object
     */
    public Quote viewQuote(String quoteId) {
        QuoteBroker quoteBroker = new QuoteBroker();
        Quote quote = null;
              
        try {
            int intQuoteId = Integer.parseInt(quoteId);
            quote = quoteBroker.getById(intQuoteId);
        }
        catch (NumberFormatException ex) {    
        }

        return quote;
    }

    /**
     * Deletes an existing Quote object from database based on its unique quote ID.
     *
     * @param quoteId unique quote ID as a String
     * @return status of deletion
     */
    public String delete(String quoteId) {
        QuoteBroker quoteBroker = new QuoteBroker();
        String status = "";
        
        try {
            int intQuoteId = Integer.parseInt(quoteId);
            
            Quote deletedQuote = quoteBroker.getById(intQuoteId);
            status = quoteBroker.delete(deletedQuote);
        }
        catch (NumberFormatException ex) {
            status = "invalid Quote ID";
        }

        return status;
    }
    
    /**
     * Searches through all existing quote objects for matching keyword using quoteBroker.
     *
     * @param keyword search term entered
     * @return  list of quote Objects which match keyword
     */
    public List<Quote> searchQuote(String keyword) {
        QuoteBroker quoteBroker = new QuoteBroker();
        return quoteBroker.getAll();
    }
}