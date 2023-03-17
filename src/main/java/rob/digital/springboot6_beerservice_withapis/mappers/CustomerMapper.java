package rob.digital.springboot6_beerservice_withapis.mappers;

import org.mapstruct.Mapper;
import rob.digital.springboot6_beerservice_withapis.entities.Customer;
import rob.digital.springboot6_beerservice_withapis.models.CustomerDTO;

@Mapper
public interface CustomerMapper {

    Customer customerDTOtoCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
