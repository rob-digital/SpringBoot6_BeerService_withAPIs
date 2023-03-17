package rob.digital.springboot6_beerservice_withapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rob.digital.springboot6_beerservice_withapis.entities.Customer;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
