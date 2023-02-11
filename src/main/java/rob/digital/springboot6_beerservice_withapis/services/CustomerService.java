package rob.digital.springboot6_beerservice_withapis.services;

import rob.digital.springboot6_beerservice_withapis.models.Customer;

import java.util.Set;
import java.util.UUID;

public interface CustomerService {

    Set<Customer> listCustomers();
    Customer getCustomerById(UUID id);

    Customer saveNewCustomer(Customer customer);

    void updateCustomerById(UUID customerId, Customer customer);

    void deleteCustomerById(UUID customerId);
}
