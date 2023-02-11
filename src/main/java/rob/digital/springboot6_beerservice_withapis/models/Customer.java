package rob.digital.springboot6_beerservice_withapis.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Customer {

    private  String name;
    private UUID id;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
