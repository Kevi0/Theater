package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.theaterMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationTheater.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;
import org.springframework.stereotype.Component;

@Component
public class TheaterMapper {

    public Theater theaterDTOToTheater(InputDTO theaterDTO){
        if(!(theaterDTO instanceof TheaterDTO theaterData)){
            return null;
        }

        Theater theater = new Theater();

        theater.setName(theaterData.getName());
        theater.setCity(theaterData.getCity());
        theater.setTel(theaterData.getTel());
        theater.setEmail(theaterData.getEmail());
        theater.setIva(theaterData.getIva());
        theater.setPec(theaterData.getPec());
        theater.setTaxCode(theaterData.getTaxCode());
        theater.setWebSite(theaterData.getWebSite());
        theater.setUniqueCode(theaterData.getUniqueCode());
        theater.setRecipientCode(theaterData.getRecipientCode());

        return theater;

    }

}
