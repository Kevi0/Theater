package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.user;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UpdatePasswordRequestDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordRequestMapper {

    public String userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof UpdatePasswordRequestDTO updateData))
            throw new ClassCastException("Errore di conversione");
        else {
            User user = new User();

            user.setPassword(updateData.getPassword());

            return user.getPassword();
        }
    }

}
