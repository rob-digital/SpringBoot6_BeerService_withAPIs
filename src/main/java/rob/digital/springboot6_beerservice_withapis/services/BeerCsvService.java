package rob.digital.springboot6_beerservice_withapis.services;

import rob.digital.springboot6_beerservice_withapis.models.BeerCSVrecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService {

    List<BeerCSVrecord> convertCSV(File csvFile);
}
