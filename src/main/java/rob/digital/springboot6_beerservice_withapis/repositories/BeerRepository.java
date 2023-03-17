package rob.digital.springboot6_beerservice_withapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rob.digital.springboot6_beerservice_withapis.entities.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

    List<Beer> findAllByBeerNameIsLikeIgnoreCase(String beerName);
}
