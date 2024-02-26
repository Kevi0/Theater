package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubArtistDTO {

    private String taxCode;

    private String gender;

    private LocalDate birthDate;

    private String birthPlace;

    private String birthState;

    private String educationTitle;

    private Boolean isEuropean;

    private String stateOfCitizenship;

    private String tel1;

    private String tel2;

    private String pec;

    private String website;

    private String residence;

    private String postalCodeOfResidence;

    private String cityOfResidence;

    private String provinceOfResidence;

    private String regionOfResidence;

    private String stateOfResidence;

    private String domicile;

    private String postalCodeOfDomicile;

    private String cityOfDomicile;

    private String provinceOfDomicile;

    private String regionOfDomicile;

    private String stateOfDomicile;

    private String stageName;

    private String instrument;

    private String voice;

    private Boolean isIva;

    private String iva;

    private String taxation;

    private String inpsNumber;

    private Boolean memberFrom;

    private String occupation;

    private Long idUser;

    private Set<String> idTypologies;

}
