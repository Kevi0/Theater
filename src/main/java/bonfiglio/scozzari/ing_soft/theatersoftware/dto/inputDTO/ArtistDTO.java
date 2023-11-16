package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO.SubArtistDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO.SubBankAccountDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO.SubWorkDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO implements InputDTO {

    private SubArtistDTO subArtistDTO;
    private SubWorkDTO subWorkDTO;
    private SubBankAccountDTO subBankAccountDTO;
}