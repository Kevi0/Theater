package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDTO implements InputDTO {

    private String name;

    private String city;

    private String tel;

    private String email;

    private String pec;

    private String website;

    private String iva;

    private String uniqueCode;

    private String recipientCode;

    private Set<Long> idUsers;

}
