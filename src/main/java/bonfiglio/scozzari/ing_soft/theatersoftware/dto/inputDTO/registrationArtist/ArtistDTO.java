package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.subRegistrationArtistDTO.SubArtistDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.subRegistrationArtistDTO.interfaces.SubBankAccountDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.subRegistrationArtistDTO.interfaces.SubWorkDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO implements InputDTO {

    private SubArtistDTO subArtistDTO;
    private SubBankAccountDTO subBankAccountDTO;
    private SubWorkDTO subWorkDTO;

}
