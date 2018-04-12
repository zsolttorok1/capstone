package businesslogic;

import domainmodel.Customer;
import domainmodel.Item;
import domainmodel.Job;
import domainmodel.User;
import java.util.List;

/**
 * Search Service handles all the operations regarding the Search Function, allowing searches for Users, Jobs, Items and Customers.
 * 
 */
public class SearchService {
            
    /**
     * Searches the database for User Objects matching the keyword parameter.
     *
     * @param keyword word to match to User's username
     * @return list of Users matching, if any
     */
    public List<User> searchUser(String keyword) {
        UserService userService = new UserService();
        List<User> userList = userService.searchUser(keyword);
       
        return userList; 
    }
    
    /**
     * Searches the database for Item Objects matching the keyword parameter.
     *
     * @param keyword word to match to Item's item name
     * @return list of Items matching, if any
     */
    public List<Item> searchItem(String keyword) {
        ItemService itemService = new ItemService();
        List<Item> itemList = itemService.searchItem(keyword);
       
        return itemList; 
    }
    
    /**
     * Searches the database for Customer Objects matching the keyword parameter.
     *
     * @param keyword word to match to Customer's name
     * @return list of Customers matching, if any
     */
    public List<Customer> searchCustomer(String keyword) {
        CustomerService customerService = new CustomerService();
        List<Customer> customerList = customerService.searchCustomer(keyword);
       
        return customerList; 
    }
    
    /**
     * Searches the database for Job Objects matching the keyword parameter.
     *
     * @param keyword word to match to Job's name
     * @return list of Jobs matching, if any
     */
    public List<Job> searchJob(String keyword) {
        JobService jobService = new JobService();
        List<Job> jobList = jobService.searchJob(keyword);
       
        return jobList;
    }
}
