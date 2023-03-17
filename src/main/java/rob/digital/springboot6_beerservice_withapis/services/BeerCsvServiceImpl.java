package rob.digital.springboot6_beerservice_withapis.services;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import rob.digital.springboot6_beerservice_withapis.models.BeerCSVrecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class BeerCsvServiceImpl implements BeerCsvService {
    @Override
    public List<BeerCSVrecord> convertCSV(File csvFile) {

        try {
            List<BeerCSVrecord> beerCsvRecords = new CsvToBeanBuilder<BeerCSVrecord>(new FileReader(csvFile))
                    .withType(BeerCSVrecord.class)
                    .build().parse();

            return beerCsvRecords;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
