package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationAgency;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgencyDTO implements InputDTO {

    @NotBlank(message = "Il campo 'name' non può essere vuoto")
    @Size(max = 255, message = "Il campo 'name' può contenere al massimo 255 caratteri")
    private String name;

    @Email(message = "Il campo 'email' deve essere un indirizzo email valido")
    @Size(max = 255, message = "Il campo 'email' può contenere al massimo 255 caratteri")
    private String email;

    @Email(message = "Il campo 'pec' deve essere un indirizzo email valido")
    @Size(max = 255, message = "Il campo 'pec' può contenere al massimo 255 caratteri")
    private String pec;

    @Pattern(regexp = "/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$/", message = "Il campo 'tel1' può contenere solo numeri e inserisci il prefisso")
    @Size(max = 20, message = "Il campo 'tel1' può contenere al massimo 20 caratteri")
    private String tel1;

    @Pattern(regexp = "/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$/", message = "Il campo 'tel2' può contenere solo numeri e inserisci il prefisso")    @Size(max = 20, message = "Il campo 'tel2' può contenere al massimo 20 caratteri")
    private String tel2;

    @Size(max = 255, message = "Il campo 'webSite' può contenere al massimo 255 caratteri")
    private String webSite;

}
