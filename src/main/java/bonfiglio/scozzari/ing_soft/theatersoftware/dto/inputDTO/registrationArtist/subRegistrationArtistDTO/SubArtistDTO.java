package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.subRegistrationArtistDTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubArtistDTO{

    @NotBlank(message = "Il genere è obbligatorio")
    private String gender;

    @NotBlank(message = "La data di nascita è obbligatoria")
    @Past(message = "La data di nascita deve essere nel passato")
    private LocalDate birthDate;

    @NotBlank(message = "Il luogo di nascita è obbligatorio")
    @Size(max = 255, message = "Il luogo di nascita deve contenere meno di 255 caratteri")
    private String birthPlace;

    @NotBlank(message = "La provincia di nascita è obbligatoria")
    @Size(max = 255, message = "La provincia di nascita deve contenere meno di 255 caratteri")
    private String birthState;

    @NotBlank(message = "Il titolo di studio è obbligatorio")
    @Size(max = 255, message = "Il titolo di studio deve contenere meno di 255 caratteri")
    private String educationTitle;

    @NotNull(message = "Il cittadino europeo è obbligatorio")
    private Boolean isEuropean;

    @NotBlank(message = "Lo stato di cittadinanza è obbligatorio")
    private String stateOfCitizenship;

    @NotBlank(message = "Il cellulare 1 non può essere vuoto")
    @Pattern(regexp = "/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$/", message = "Il campo 'tel1' può contenere solo numeri e inserisci il prefisso")
    private String tel1;

    @Pattern(regexp = "\\d{10}", message = "Deve essere un numero di telefono valido (10 cifre)")
    private String tel2;

    @Email(message = "Il campo 'pec' deve essere un indirizzo email valido")
    @Size(max = 255, message = "La PEC deve contenere meno di 255 caratteri")
    private String pec;

    @Size(max = 255, message = "Il sito web deve contenere meno di 255 caratteri")
    private String website;

    @NotBlank(message = "La residenza non può essere vuota")
    @Size(max = 255, message = "La residenza deve contenere meno di 255 caratteri")
    private String residence;

    @NotBlank(message = "Il codice postale della residenza non può essere vuoto")
    @Size(max = 10, message = "Il codice postale deve contenere 10 caratteri")
    private String postalCodeOfResidence;

    @NotBlank(message = "La città di residenza non può essere vuota")
    @Size(max = 255, message = "La città di residenza deve contenere meno di 255 caratteri")
    private String cityOfResidence;

    @NotBlank(message = "La provincia di residenza non può essere vuota")
    @Size(max = 255, message = "La provincia di residenza deve contenere meno di 255 caratteri")
    private String provinceOfResidence;

    @NotBlank(message = "La regione di residenza non può essere vuota")
    @Size(max = 255, message = "La regione di residenza deve contenere meno di 255 caratteri")
    private String regionOfResidence;

    @NotBlank(message = "Lo stato di residenza non può essere vuoto")
    @Size(max = 255, message = "Lo stato di residenza deve contenere meno di 255 caratteri")
    private String stateOfResidence;

    @NotBlank(message = "Il domicilio non può essere vuoto")
    @Size(max = 255, message = "Il domicilio deve contenere meno di 255 caratteri")
    private String domicile;

    @NotBlank(message = "Il codice postale del domicilio non può essere vuoto")
    @Size(max = 10, message = "Il codice postale deve contenere 10 caratteri")
    private String postalCodeOfDomicile;

    @NotBlank(message = "La città del domicilio non può essere vuota")
    @Size(max = 255, message = "La città del domicilio deve contenere meno di 255 caratteri")
    private String cityOfDomicile;

    @NotBlank(message = "La provincia del domicilio non può essere vuota")
    @Size(max = 255, message = "La provincia del domicilio deve contenere meno di 255 caratteri")
    private String provinceOfDomicile;

    @NotBlank(message = "La regione del domicilio non può essere vuota")
    @Size(max = 255, message = "La regione del domicilio deve contenere meno di 255 caratteri")
    private String regionOfDomicile;

    @NotBlank(message = "Lo stato del domicilio non può essere vuoto")
    @Size(max = 255, message = "Lo stato del domicilio deve contenere meno di 255 caratteri")
    private String stateOfDomicile;

    @Size(max = 255, message = "Il nome d'arte deve contenere meno di 255 caratteri")
    private String stageName;

    @Size(max = 50, message = "Lo strumento deve contenere meno di 50 caratteri")
    private String instrument;

    @Size(max = 50, message = "La voce deve contenere meno di 50 caratteri")
    private String voice;

    @NotNull(message = "Il campo 'IsIva' non può essere nullo")
    private Boolean isIva;

    @Size(max = 12, message = "L'IVA deve contenere meno di 12 caratteri")
    private String iva;

    private String taxation;

    @Pattern(regexp = "\\d{10}", message = "Deve essere un numero INPS valido (10 cifre)")
    @NotBlank(message = "Il numero INPS non può essere nullo")
    private String inpsNumber;

    @NotBlank(message = "Il campo 'MemberFrom' non può essere nullo")
    private Boolean memberFrom;

    @NotBlank(message = "L'occupazione non può essere nulla")
    @Size(max = 255, message = "L'occupazione deve contenere meno di 255 caratteri")
    private String occupation;

    private Long idUser;

    private Set<String> idTypologies;

}
