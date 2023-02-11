package rob.digital.springboot6_beerservice_withapis.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerTest {

    @Autowired
    BeerController beerController;

    @Test
    void getBeerById() {

        System.out.println("Test Beer Controller " + beerController.getBeerById(UUID.randomUUID()));
    }
}