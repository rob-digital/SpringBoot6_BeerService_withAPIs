package rob.digital.springboot6_beerservice_withapis.services;

import rob.digital.springboot6_beerservice_withapis.models.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface BeerService {

    List<BeerDTO> listBeers(String beerName);

    Optional<BeerDTO> getBeerById(UUID id);
    BeerDTO saveNewBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    Boolean deleteById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer);
}
