package WebUiCrud.Controller;

import WebUiCrud.Entity.Customer;
import WebUiCrud.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    //define field for service
    private CustomerService customerService;

    //invoke
    @Autowired
    public CustomerRestController(CustomerService customerService)
    {
        this.customerService=customerService;
    }

    //GET method to find the list of customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomer()
    {
        return customerService.getAllCustomers();
    }

    //GET method to find the customers by id
    @GetMapping("/customers/{customersId}")
    public Customer getCustomersById(@PathVariable Integer customersId)
    {
        return customerService.getCustomersById(customersId);
    }

    //POST method to add the customers
    @PostMapping("/customers")
    public Customer addCustomers(@RequestBody Customer customer)
    {
        return customerService.addUpdateCustomers(customer);
    }

    //PUT method to edit the customers details
    @PutMapping("/customers")
    public Customer updateCustomers(@RequestBody Customer customer)
    {
        return customerService.addUpdateCustomers(customer);
    }

    // DELETE method to delete the customers by Id
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomers(@PathVariable Integer customerId)
    {
        customerService.deleteCustomers(customerId);
        return "Deleted Customer Id: "+customerId;
    }
}
