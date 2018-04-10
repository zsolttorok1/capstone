package businesslogic;

import domainmodel.Customer;
import domainmodel.Item;
import domainmodel.Job;
import domainmodel.User;
import java.util.List;

public class SearchService {
            
    public List<User> searchUser(String keyword) {
        UserService userService = new UserService();
        List<User> userList = userService.searchUser(keyword);
       
        return userList; 
    }
    
    public List<Item> searchItem(String keyword) {
        ItemService itemService = new ItemService();
        List<Item> itemList = itemService.searchItem(keyword);
       
        return itemList; 
    }
    
    public List<Customer> searchCustomer(String keyword) {
        CustomerService customerService = new CustomerService();
        List<Customer> customerList = customerService.searchCustomer(keyword);
       
        return customerList; 
    }
    
    public List<Job> searchJob(String keyword) {
        JobService jobService = new JobService();
        List<Job> jobList = jobService.searchJob(keyword);
       
        return jobList;
    }
}
