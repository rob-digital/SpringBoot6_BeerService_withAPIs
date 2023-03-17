package rob.digital.springboot6_beerservice_withapis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = " ========= Value not Found. Go away! ===========")
public class NotFoundException extends RuntimeException{

}
