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
                || description.isEmpty() || email.isEmpty()){
            
            return "error";
        }

      
        Quote quote = new Quote(name, description, email);

        QuoteBroker jobBroker = new QuoteBroker();
        return jobBroker.insert(quote);
    }

    public Quote viewQuote(String name) {
        QuoteBroker quoteBroker = new QuoteBroker();

        Quote quote = quoteBroker.getByName(name);

        return quote;
    }

    public List<Quote> searchQuote(String keyword) {
        QuoteBroker quoteBroker = new QuoteBroker();

        //this always return all items for now
        return quoteBroker.getAll();
    }

    public String delete(String name) {
        QuoteBroker quoteBroker = new QuoteBroker();
        Quote deletedQuote = quoteBroker.getByName(name);
        return quoteBroker.delete(deletedQuote);
    }

    
}