package WebUiCrud.Controller;

import WebUiCrud.Entity.Customer;
import WebUiCrud.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    //define field for services
    private  final CustomerService customerService;

    //add mapping for "/list"
    @GetMapping("/list")
    public String listCustomers(Model themodel)
    {
        //get the list of customers from db
        List<Customer> customerList=customerService.getAllCustomers();

        //add to the spring model
        themodel.addAttribute("customers",customerList);

        return "listcustomers";
    }

    //add mapping for "/add"
    @GetMapping("/add")
    public  String addCustomers(Model themodel)
    {
        //create a model attribute to bind form data
        Customer customer= new Customer();

        //add to the spring model
        themodel.addAttribute("customers",customer);
        return "addcustomers";
    }

    @GetMapping("/update")
    public String updateCustomers(@RequestParam("customerId") int Id,Model themodel)
    {
     //get the customer the service
        Customer thecustomer= customerService.getCustomersById(Id);

        //set the customer in the model to prepopulate the form
        themodel.addAttribute("customers",thecustomer);

        //send over to our form
        return "update-customers";
    }

    @GetMapping("/delete")
    public String deleteCustomers(@RequestParam("customerId") int id)
    {
        //delete the customers
        customerService.deleteCustomers(id);

        //redirect to the customers list
        return "redirect:/customers/list";

    }

    @PostMapping("/save")
    public String saveCustomers(@ModelAttribute("customers") Customer customer)
    {
        //save the customers
        customerService.addUpdateCustomers(customer);

        //use redirect to prevent duplicate submissions
        return "redirect:/customers/list";
    }

    @GetMapping("/view")
    public String viewCustomer(@RequestParam("customerId") int customerId, Model model) {
        // Retrieve the customer details by the provided customerId
        Customer customer = customerService.getCustomersById(customerId);

        // Add the customer object to the model to pass it to the view
        model.addAttribute("customer", customer);

        // Return the name of the view template to display the customer details
        return "customer-details";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("query") String query, Model model) {
        // Perform the search logic based on the query
        List<Customer> searchResults = customerService.searchCustomers(query);

        // Add the search results to the model
        model.addAttribute("customers", searchResults);

        return "customer-directory";
    }




}
