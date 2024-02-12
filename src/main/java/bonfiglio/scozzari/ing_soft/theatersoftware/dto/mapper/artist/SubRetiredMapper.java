package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubRetiredDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Retired;
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
