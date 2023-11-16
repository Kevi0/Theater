package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.subArtistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO.SubUnemployedDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Unemployed;
import org.springframework.stereotype.Component;

@Component
public class SubUnemployedMapper {
    public static Unemployed SubUnemployedDTOToUnemployed (SubUnemployedDTO subUnemployedDTO) {
        if (subUnemployedDTO == null) {
            return null;
        }

        Unemployed unemployed = new Unemployed();
        unemployed.setAllowance(subUnemployedDTO.getAllowance());

        return unemployed;
    }
}
