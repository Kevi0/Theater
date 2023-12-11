package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.artistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.subRegistrationArtistDTO.SubRetiredDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Retired;
import org.springframework.stereotype.Component;

@Component
public class SubRetiredMapper {

    public Retired subRetiredDTOToRetired(
            SubRetiredDTO subRetiredDTO
    ){
        if(subRetiredDTO == null){
            return null;
        }

        Retired retired = new Retired();

        retired.setIsOver65(subRetiredDTO.getIsOver65());

        return retired;

    }

}
