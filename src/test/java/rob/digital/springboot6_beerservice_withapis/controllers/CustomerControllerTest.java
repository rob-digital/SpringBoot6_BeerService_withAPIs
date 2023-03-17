package rob.digital.springboot6_beerservice_withapis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rob.digital.springboot6_beerservice_withapis.models.CustomerDTO;
import rob.digital.springboot6_beerservice_withapis.services.CustomerService;
import rob.digital.springboot6_beerservice_withapis.services.CustomerServiceImpl;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<CustomerDTO> customerArgumentCaptor;
    @BeforeEach
    void setUp() {
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Test
    void allCustomers() throws Exception {

        given(customerService.listCustomers()).willReturn(customerServiceImpl.listCustomers());

        mockMvc.perform(get("/api/v1/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3))
                );

    }

    @Test
    void getCustomerById() throws Exception {

        CustomerDTO testCustomer = customerServiceImpl.listCustomers().stream().findFirst().get();

        given(customerService.getCustomerById(testCustomer.getId())).willReturn(Optional.of(testCustomer));

        mockMvc.perform(get("/api/v1/customers/" + testCustomer.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", is(testCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is(testCustomer.getName()))
                );
    }

    @Test
    void createNewCustomerTest() throws Exception {

        CustomerDTO customer = customerServiceImpl.listCustomers().stream().findFirst().get();

        given(customerService.saveNewCustomer(any(CustomerDTO.class))).willReturn(customerServiceImpl.listCustomers().stream().findFirst().get());

        mockMvc.perform(post("/api/v1/customers")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
            .andExpect(status().isCreated())
            .andExpect(header().exists("Location"));

        System.out.println(objectMapper.writeValueAsString(customer));
        System.out.println("======================================");
        System.out.println(customerService.saveNewCustomer(any(CustomerDTO.class)));
        System.out.println("======================================");
        System.out.println(customerServiceImpl.listCustomers().stream().findFirst().get());

    }

    @Test
    void updateCustomerTest() throws Exception {

        CustomerDTO customer = customerServiceImpl.listCustomers().stream().findAny().get();

        customer.setName("New customer");

        mockMvc.perform(put("/api/v1/customers/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());

        verify(customerService).updateCustomerById(any(UUID.class), any(CustomerDTO.class));
    }

    @Test
    void deleteCustomerTest() throws Exception {

        CustomerDTO customer = customerServiceImpl.listCustomers().stream().findAny().get();

        mockMvc.perform(delete("/api/v1/customers/" + customer.getId()))
                .andExpect(status().isNoContent());


        verify(customerService).deleteCustomerById(uuidArgumentCaptor.capture());

        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());

    }

//    @Test
//    void patchCustomerTest() throws Exception{
//
//        Customer customer = customerServiceImpl.listCustomers().stream().findAny().get();
//
//        Map<String, Object> customerMap = new HashMap<>();
//        customerMap.put("name", "Mr R");
//
//        mockMvc.perform(patch("/api/v1/customers/" + customer.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(customerMap)))
//                .andExpect(status().isNoContent());
//
//        // this method was not implemented on the Controller
//        verify(customerService).patchCustomerById(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());
//
//        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
//        assertThat(customerMap.get("name")).isEqualTo(customerArgumentCaptor.getValue().getName());
//    }

    // ------------------------------------- Exception ------------------------


    @Test
    void getCustomerByIdNotFound() throws Exception {

        //NOtFoundException is handle by @ResponseStatus if we want to
        // get NotFoundException specified in the @ResponseStatus
        // otherwise you can use the @ControllerAdvice for global - see ExceptionController
        given(customerService.getCustomerById(any(UUID.class))).willThrow(NotFoundException.class);

        mockMvc.perform(get("/api/v1/customers/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }
}
