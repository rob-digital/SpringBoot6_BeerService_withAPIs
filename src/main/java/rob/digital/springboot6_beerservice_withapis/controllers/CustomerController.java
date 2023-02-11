package rob.digital.springboot6_beerservice_withapis.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rob.digital.springboot6_beerservice_withapis.models.Customer;
import rob.digital.springboot6_beerservice_withapis.services.CustomerService;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    Set<Customer> allCustomers() {
        return customerService.listCustomers();
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID id) {
        log.debug("###### --- Get customer by ID controller --- ######333");

        return customerService.getCustomerById(id);
    }

    @PostMapping
    public ResponseEntity postCustomer(@RequestBody Customer customer) {

        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity updateCustomerByID(@PathVariable("customerId") UUID customerId,
                                             @RequestBody Customer customer){

        customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity deleteCustomerById(@PathVariable("customerId") UUID customerId){

        customerService.deleteCustomerById(customerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
