package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.agency;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencyDTO implements InputDTO {

    private String name;

    private String email;

    private String pec;

    private String tel1;

    private String tel2;

    private String website;

    private Set<Long> idUsers;

}
