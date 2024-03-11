package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user;

import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.UserRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSummaryDTO {

    private String name;
    private String surname;
    private String username;
    private String email;
    private UserRoles role;

    // Costruttore che accetta un oggetto User e ne estrae solo le informazioni desiderate
    public UserSummaryDTO(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

}
