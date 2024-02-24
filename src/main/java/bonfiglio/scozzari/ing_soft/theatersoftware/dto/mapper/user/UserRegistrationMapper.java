package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.user;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserRegistrationDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    public User userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof UserRegistrationDTO userData))
            throw new ClassCastException("Errore di conversione");
        else {
            User user = new User();

            user.setName(userData.getName());
            user.setSurname(userData.getSurname());
            user.setEmail(userData.getEmail());
            user.setUsername(userData.getUsername());
            user.setPassword(userData.getPassword());
            return user;
        }
    }

}
