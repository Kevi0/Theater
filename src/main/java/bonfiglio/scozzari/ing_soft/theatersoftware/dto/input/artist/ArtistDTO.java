package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubArtistDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.interfaces.SubBankAccountDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.interfaces.SubWorkDTO;
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
