package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.user;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserAuthenticationDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserRegistrationDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationMapper {

    public User userDTOToUser(InputDTO userDTO) {
        if (!(userDTO instanceof UserAuthenticationDTO userData))
            return null;
        else {
            User user = new User();

            user.setUsername(userData.getUsername());
            user.setPassword(userData.getPassword());
            return user;
        }
    }

}
