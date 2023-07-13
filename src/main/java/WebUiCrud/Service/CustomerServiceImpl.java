package WebUiCrud.Service;

import WebUiCrud.Entity.Customer;
import WebUiCrud.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    //define field for repository
    private CustomerRepository customerRepository;

    //invoke
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository)
    {
        this.customerRepository=customerRepository;
    }
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Customer getCustomersById(Integer customerId) {
        Optional<Customer> result=customerRepository.findById(customerId);
        Customer customer=null;

        if(result.isPresent())
        {
            customer=result.get();
        }else
        {
            throw new RuntimeException("Customer not found with id: "+customerId);
        }
        return customer;
    }

    @Override
    public Customer addUpdateCustomers(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomers(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> searchCustomers(String query) {
        // Perform the search operation based on the query
        List<Customer> searchResults = new ArrayList<>();

        // Search by customer id
        try {
            int customerId = Integer.parseInt(query);
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            optionalCustomer.ifPresent(searchResults::add);
        } catch (NumberFormatException ignored) {
            // Ignore if the query cannot be parsed as an integer
        }

        // Search by first name, last name, and email
        List<Customer> searchByOtherFields = customerRepository.findByFirstNameContainingOrLastNameContainingOrEmailContainingIgnoreCase(query, query, query);
        searchResults.addAll(searchByOtherFields);

        return searchResults;
    }

}
