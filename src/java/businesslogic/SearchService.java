package businesslogic;

import dataaccess.CustomerBroker;
import domainmodel.Customer;
import domainmodel.Item;
import domainmodel.User;
import java.util.ArrayList;
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
}
