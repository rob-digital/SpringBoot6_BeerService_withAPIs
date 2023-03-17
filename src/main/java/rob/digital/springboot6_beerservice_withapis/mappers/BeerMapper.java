package rob.digital.springboot6_beerservice_withapis.mappers;

import org.mapstruct.Mapper;
import rob.digital.springboot6_beerservice_withapis.entities.Beer;
import rob.digital.springboot6_beerservice_withapis.models.BeerDTO;

@Mapper
public interface BeerMapper {

    Beer beerDTOtoBeer(BeerDTO dto);

    BeerDTO beerToBeerDTO(Beer beer);
}
