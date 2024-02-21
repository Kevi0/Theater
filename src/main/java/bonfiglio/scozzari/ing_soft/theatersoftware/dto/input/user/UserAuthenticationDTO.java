package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationDTO implements InputDTO {

    private String username;
    private String password;

}
