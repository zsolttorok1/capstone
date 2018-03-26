package businesslogic;

import dataaccess.CustomerBroker;
import domainmodel.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
        
    public String insert(String customerName, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String firstName, String lastName, String companyName, String email, String position, String notes) {
    
        Customer customer = build(customerName, houseNumber, street, city, province, country, postalCode, phoneNumberList,  firstName, lastName, companyName, email, position, notes);
        
        String status = validate(customer);
        
        if (status != null && status.equals("ok")) {
            CustomerBroker customerBroker = CustomerBroker.getInstance();
            status = customerBroker.insert(customer);
        }

        return status; 
    }

    public Customer getByCustomerName(String customerName) {
        
        if (customerName != null && !customerName.isEmpty()) {
            CustomerBroker customerBroker = CustomerBroker.getInstance();
            return customerBroker.getByName(customerName);
        }
        else {
            return null;
        }
    }

    public List<Customer> searchCustomer(String keyword) {
        CustomerBroker customerBroker = CustomerBroker.getInstance();
        
        //this always return all items for now
        List<Customer> customerList = customerBroker.getAll();
        
        if (customerList == null)
            return null;
                
        return customerList;
    }

    public String update(String customerName, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String firstName, String lastName, String companyName, String email, String position, String notes) {
        Customer customer = build(customerName, houseNumber, street, city, province, country, postalCode, phoneNumberList,  firstName, lastName, companyName, email, position, notes);
        
        return update(customer);
    }
    
    public String update(Customer customerNew) {
        
        CustomerBroker customerBroker = CustomerBroker.getInstance();
        Customer customer = null;
        String status = "";
        
        if (customerNew.getCompanyName() != null) 
            customer = customerBroker.getByName(customerNew.getCompanyName());
        else 
            return "Invalid customerName";
        
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
            return customerBroker.update(customer);
        }
        else {
            return status;
        }
    }
      
    private String validate(Customer customer) {
        String status = "";
        
        if (customer == null || customer.getCompanyName().isEmpty()) {
            status += "invalid customerName ";
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
    
    private Customer build(String customerName, String houseNumber, String street, String city, String province, String country, String postalCode, String[] phoneNumberList, String firstName, String lastName, String companyName, String email, String position, String notes) {
        Customer customer = new Customer();
        
        if (customerName != null && !customerName.isEmpty()) {
            customer.setCustomerName(customerName);
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
    
    public String delete(String customerName) {
        CustomerBroker customerBroker = CustomerBroker.getInstance();
        Customer deletedCustomer = customerBroker.getByName(customerName);
        
        return customerBroker.delete(deletedCustomer);
    }
}
