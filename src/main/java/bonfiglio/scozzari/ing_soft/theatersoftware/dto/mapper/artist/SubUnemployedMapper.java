package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubUnemployedDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Unemployed;
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
