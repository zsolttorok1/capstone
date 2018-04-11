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
 *
 * @author 742227
 */
public class QuoteService {
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
    
    public List<Quote> searchQuote(String keyword) {
        QuoteBroker quoteBroker = new QuoteBroker();
        return quoteBroker.getAll();
    }
}