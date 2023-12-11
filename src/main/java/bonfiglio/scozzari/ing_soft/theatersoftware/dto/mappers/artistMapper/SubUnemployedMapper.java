package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.artistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.subRegistrationArtistDTO.SubUnemployedDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Unemployed;
import org.springframework.stereotype.Component;

@Component
public class SubUnemployedMapper {

    public Unemployed subUnemployedDTOToUnemployed(
            SubUnemployedDTO subUnemployedDTO
    ){
        if(subUnemployedDTO == null){
            return null;
        }

        Unemployed unemployed = new Unemployed();

        unemployed.setAllowance(subUnemployedDTO.getAllowance());

        return unemployed;

    }

}
