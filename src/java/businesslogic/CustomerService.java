package businesslogic;

import dataaccess.CustomerBroker;
import domainmodel.Customer;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer Service handles all the operations regarding the Customer Object, including inserting, deleting, updating
 * and building.
 * 
 * 
 */
public class CustomerService {
        
    /**
     * Insert calls the CustomerBroker class in order to insert a Customer Object into the database.
     *
     * @param customerId the unique customer ID as a String
     * @param houseNumber customer's houseNumber as a String
     * @param street customer's streetname as a String
     * @param city customer's city as a String
     * @param province customer's province as a String
     * @param country customer's country as a String
     * @param postalCode customer's postal code as a String
     * @param phoneNumberList customer's phone number(s) as string(s)
     * @param firstName customer's first name as a String
     * @param lastName customer's last name as a String
     * @param companyName customer's company name as a String
     * @param email customer's email address as a String
     * @param position customer's position within their company as a string
     * @param notes employee notes on customer as a string 
     * @return status of insertion in database
     */
    public String insert(String customerId, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String firstName, String lastName, String companyName, String email, String position, String notes) {
    
        Customer customer = build(customerId, houseNumber, street, city, province, country, postalCode, phoneNumberList,  firstName, lastName, companyName, email, position, notes);
        
        String status = validate(customer);
        
        if (status != null && status.equals("ok")) {
            CustomerBroker customerBroker = CustomerBroker.getInstance();
            status = customerBroker.insert(customer);
        }
        
        if (status != null && status.contains("inserted")) {
            status = "Customer Successfully Added";
        }
        else if (status != null && status.contains("updated")) {
            status = "Customer Successfully Updated";
        }

        return status; 
    }

    /**
     * Retrieves customer Object (if found) by its unique ID from the Broker.
     *
     * @param customerId Customer's unique ID
     * @return customer Object
     */
    public Customer getByCustomerId(String customerId) {
        try {
            int intCustomerId = Integer.parseInt(customerId);
            CustomerBroker customerBroker = CustomerBroker.getInstance();
            
            return customerBroker.getById(intCustomerId);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    /**
     * Searches through all existing customers for matching keyword using customerBroker.
     *
     * @param keyword search term entered
     * @return list of customer(s)
     */
    public List<Customer> searchCustomer(String keyword) {
        CustomerBroker customerBroker = CustomerBroker.getInstance();

        List<Customer> customerList = null;
        
        if (!keyword.isEmpty()) {
            customerList = customerBroker.search(keyword);
        }
        else {
            customerList = customerBroker.getAll();
        }
        
        if (customerList == null)
            return null;
                
        return customerList;
    }

    /**
     * Updates existing Customer Object by setting all parameters to updated ones.
     *
     * @param customerId the unique customer ID as a String
     * @param houseNumber customer's houseNumber as a String
     * @param street customer's streetname as a String
     * @param city customer's city as a String
     * @param province customer's province as a String
     * @param country customer's country as a String
     * @param postalCode customer's postal code as a String
     * @param phoneNumberList customer's phone number(s) as string(s)
     * @param firstName customer's first name as a String
     * @param lastName customer's last name as a String
     * @param companyName customer's company name as a String
     * @param email customer's email address as a String
     * @param position customer's position within their company as a string
     * @param notes employee notes on customer as a string 
     * @return
     */
    public String update(String customerId, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String firstName, String lastName, String companyName, String email, String position, String notes) {
        Customer customer = build(customerId, houseNumber, street, city, province, country, postalCode, phoneNumberList,  firstName, lastName, companyName, email, position, notes);
        
        return update(customer);
    }
    
    /**
     * Updates exisiting Customer object by matching a newly created temporary Customer object to an existing one.
     *
     * @param customerNew the newly created temporary customer object
     * @return status of update in database
     */
    public String update(Customer customerNew) {
        
        CustomerBroker customerBroker = CustomerBroker.getInstance();
        Customer customer = null;
        String status = "";
        
        if (customerNew.getCompanyName() != null) 
            customer = customerBroker.getById(customerNew.getCustomerId());
        else 
            return "Invalid customerId";
        
        if (customer == null)
            return "Customer not found while attempting to update. Check database connection.";
        
        //prepare changed attributes on the updatable User
        if (customerNew.getHouseNumber() > 0 )
            customer.setHouseNumber(customerNew.getHouseNumber());
        if (customerNew.getStreet() != null && !customerNew.getStreet().isEmpty() )
            customer.setStreet(customerNew.getStreet());
        if (customerNew.getCity() != null && !customerNew.getCity().isEmpty() )
            customer.setCity(customerNew.getCity());
        if (customerNew.getProvince() != null && !customerNew.getProvince().isEmpty() )
            customer.setProvince(customerNew.getProvince());
        if (customerNew.getCountry() != null && !customerNew.getCountry().isEmpty() )
            customer.setCountry(customerNew.getCountry());
        if (customerNew.getPostalCode() != null && !customerNew.getPostalCode().isEmpty() )
            customer.setPostalCode(customerNew.getPostalCode());
        if (customerNew.getPhoneNumberList() != null && !customerNew.getPhoneNumberList().isEmpty() )
            customer.setPhoneNumberList(customerNew.getPhoneNumberList());
        if (customerNew.getFirstName() != null && !customerNew.getFirstName().isEmpty() )
            customer.setFirstName(customerNew.getFirstName());
        if (customerNew.getLastName() != null && !customerNew.getLastName().isEmpty() )
            customer.setLastName(customerNew.getLastName());
        if (customerNew.getCompanyName() != null && !customerNew.getCompanyName().isEmpty() )
            customer.setCompanyName(customerNew.getCompanyName());
        if (customerNew.getEmail() != null && !customerNew.getEmail().isEmpty() )
            customer.setEmail(customerNew.getEmail());
        if (customerNew.getPosition() != null && !customerNew.getPosition().isEmpty() )
            customer.setPosition(customerNew.getPosition());
        if (customerNew.getNotes() != null && !customerNew.getNotes().isEmpty() )
            customer.setNotes(customerNew.getNotes());
        
        //make sure that the new attribute values are valid, before updating.
        status = validate(customer);
        
        if (status != null && status.equals("ok")) {
            status = customerBroker.update(customer);
                    
            if (status != null && status.contains("inserted")) {
                status = "Customer Successfully Added";
            }
            else if (status != null && status.contains("updated")) {
                status = "Customer Successfully Updated";
            }
        }
        
        return status;
    }
     
    /**
     * Makes sure none of the parameters in Customer object are null.
     *
     * @param customer customer object to be examined
     * @return status of validation check
     */
    private String validate(Customer customer) {
        String status = "";
        
        if (customer == null || customer.getCustomerId() < 0) {
            status += "invalid customerId ";
        }
        if (customer.getHouseNumber() <= 0) {
            status += "invalid houseNumber ";
        }
        if (customer.getStreet() == null || customer.getStreet().isEmpty()) {
            status += "invalid street ";
        }
        if (customer.getCity() == null || customer.getCity().isEmpty()) {
            status += "invalid city ";
        }
        if (customer.getProvince() == null || customer.getProvince().isEmpty()) {
            status += "invalid province ";
        }
        if (customer.getCountry() == null || customer.getCountry().isEmpty()) {
            status += "invalid country ";
        }
        if (customer.getPostalCode() == null || customer.getPostalCode().isEmpty()) {
            status += "invalid postalCode ";
        }
        if (customer.getPhoneNumberList() == null || customer.getPhoneNumberList().isEmpty()) {
            status += "invalid phoneNumberList ";
        }
        else {
            for (int i=0; i < customer.getPhoneNumberList().size(); i++) {
                //regex this check for right number of digits, format, etc
                if (customer.getPhoneNumberList().get(i) <= 0) {
                    status += "invalid phoneNumberFormat at phone entry#" + (i+1) + " ";
                }
            }
        }
        if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
            status += "invalid firstName ";
        }
        if (customer.getLastName() == null || customer.getLastName().isEmpty()) {
            status += "invalid lastName ";
        }
        if (customer.getCompanyName() == null || customer.getCompanyName().isEmpty()) {
            status += "invalid companyName ";
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            status += "invalid email ";
        }
        if (customer.getPosition() == null || customer.getPosition().isEmpty()) {
            status += "invalid position ";
        }
        if (customer.getNotes() == null || customer.getNotes().isEmpty()) {
            status += "invalid notes ";
        }
        
        if (status.isEmpty()) {
            return "ok";
        }
        else { 
            return status;
        }
    }
    
    /**
     * Builds Customer object using parameters and validates data.
     *
     * @param customerId the unique customer ID as a String
     * @param houseNumber customer's houseNumber as a String
     * @param street customer's streetname as a String
     * @param city customer's city as a String
     * @param province customer's province as a String
     * @param country customer's country as a String
     * @param postalCode customer's postal code as a String
     * @param phoneNumberList customer's phone number(s) as string(s)
     * @param firstName customer's first name as a String
     * @param lastName customer's last name as a String
     * @param companyName customer's company name as a String
     * @param email customer's email address as a String
     * @param position customer's position within their company as a string
     * @param notes employee notes on customer as a string 
     * @return customer Object
     */
    private Customer build(String customerId, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String firstName, String lastName, String companyName, String email, String position, String notes) {
        Customer customer = new Customer();
        
        if (customerId != null && !customerId.isEmpty()) {
            try {
                int intCustomerId = Integer.parseInt(customerId);
                customer.setCustomerId(intCustomerId);
            } catch (NumberFormatException ex) {
                customer.setCustomerId(-1);
            }
        }
        if (houseNumber != null && !houseNumber.isEmpty()) {
            try {
                int intHouseNumber = Integer.parseInt(houseNumber);
                customer.setHouseNumber(intHouseNumber);
            } catch (NumberFormatException ex) {
                customer.setHouseNumber(-1);
            }
        }
        if (street != null && !street.isEmpty()) {
            customer.setStreet(street);
        }
        if (city != null && !city.isEmpty()) {
            customer.setCity(city);
        }
        if (province != null && !province.isEmpty()) {
            customer.setProvince(province);
        }
        if (country != null && !country.isEmpty()) {
            customer.setCountry(country);
        }
        if (postalCode != null && !postalCode.isEmpty()) {
            customer.setPostalCode(postalCode);
        }
        if (phoneNumberList != null && phoneNumberList.length != 0) {
            ArrayList<Long> intPhoneNumberList = new ArrayList<>();
            
            for (int i=0; i < phoneNumberList.length; i++) {
                try {
                    long phoneNumber = Long.parseLong(phoneNumberList[i]);
                    intPhoneNumberList.add(phoneNumber);
                } catch (NumberFormatException ex) {
                    intPhoneNumberList.add(-1L);
                }
            }
            customer.setPhoneNumberList(intPhoneNumberList);
        }
        if (firstName != null && !firstName.isEmpty()) {
            customer.setFirstName(firstName);
        }
        if (lastName != null && !lastName.isEmpty()) {
            customer.setLastName(lastName);
        }
        if (companyName != null && !companyName.isEmpty()) {
            customer.setCompanyName(companyName);
        }
        if (email != null && !email.isEmpty()) {
            customer.setEmail(email);
        }
        if (position != null && !position.isEmpty()) {
            customer.setPosition(position);
        }
        if (notes != null && !notes.isEmpty()) {
            customer.setNotes(notes);
        }

        return customer;
    }
    
    /**
     * Deletes an existing customer object based on its customer ID.
     *
     * @param customerId the unique customer ID as a String
     * @return
     */
    public String delete(String customerId) {
        String status = "";
        
        try {
            int intCustomerId = Integer.parseInt(customerId);
            
            CustomerBroker customerBroker = CustomerBroker.getInstance();
            Customer deletedCustomer = customerBroker.getById(intCustomerId);
        
            return customerBroker.delete(deletedCustomer);
        } catch (NumberFormatException ex) {
            return "invalid Customer ID";
        }
    }
}
