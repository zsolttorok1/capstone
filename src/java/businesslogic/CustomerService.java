package businesslogic;

import dataaccess.CustomerBroker;
import domainmodel.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerService {

    public String addCustomer(String customerName, String jobName, ArrayList<Integer> phoneId, String addressId, String firstName,
            String lastName, String companyName, String email, String position, String note) {
        //check for nulls
        if (customerName == null || jobName == null || phoneId.isEmpty() || addressId == null || firstName == null
                || lastName == null || companyName == null || email == null || position == null || note == null
                || customerName.isEmpty() || jobName.isEmpty() || addressId.isEmpty() || firstName.isEmpty()
                || lastName.isEmpty() || companyName.isEmpty() || email.isEmpty()) {
            return "error";
        }

        //check for values being valid
        int intAddress = 0;
        //try catch this
        try {
            intAddress = Integer.parseInt(addressId);

        } catch (NumberFormatException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }
        Customer customer = new Customer(customerName, jobName, phoneId, intAddress, firstName, lastName,
                companyName, email, position, note);

        CustomerBroker customerBroker = new CustomerBroker();
        return customerBroker.insert(customer);
    }

    public Customer viewCustomer(String customerName) {
        CustomerBroker customerBroker = new CustomerBroker();

        Customer customer = customerBroker.getByName(customerName);

        return customer;
    }

    public List<Customer> searchCustomer(String keyword) {
        CustomerBroker customerBroker = new CustomerBroker();

        //this always return all items for now
        return customerBroker.getAll();
    }

    public String edit(String customerName, String jobName, ArrayList<Integer> phoneId, String addressId, String firstName,
            String lastName, String companyName, String email, String position, String note) {
        CustomerBroker customerBroker = new CustomerBroker();
        Customer customer = customerBroker.getByName(customerName);

        customer.setJobName(jobName);
        customer.setPhoneId(phoneId);

        int intAddress = 0;
        //try catch this
        try {
            intAddress = Integer.parseInt(addressId);

        } catch (NumberFormatException ex) {
            Logger.getLogger(CustomerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return "error";
        }

        customer.setAddressId(intAddress);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setCompanyName(companyName);
        customer.setEmail(email);
        customer.setPosition(position);
        customer.setNote(note);

        return customerBroker.update(customer);
    }

    public String delete(String customerName) {
        CustomerBroker customerBroker = new CustomerBroker();
        Customer deletedCustomer = customerBroker.getByName(customerName);
        return customerBroker.delete(deletedCustomer);
    }

    private int parseInteger(int addressId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
