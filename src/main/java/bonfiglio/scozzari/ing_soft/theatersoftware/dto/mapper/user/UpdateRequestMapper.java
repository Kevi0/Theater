package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.user;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UpdateRequestDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateRequestMapper {

    public User userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof UpdateRequestDTO updateData))
            throw new ClassCastException("Errore di conversione");
        else {
            User user = new User();

            user.setName(updateData.getName());
            user.setSurname(updateData.getSurname());
            user.setEmail(updateData.getEmail());

            return user;
        }
    }

}
