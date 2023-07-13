package WebUiCrud.Service;


import WebUiCrud.Entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer getCustomersById(Integer customerId);

    Customer addUpdateCustomers(Customer customer);

    void deleteCustomers(Integer customerId);

    List<Customer> searchCustomers(String query);
}
