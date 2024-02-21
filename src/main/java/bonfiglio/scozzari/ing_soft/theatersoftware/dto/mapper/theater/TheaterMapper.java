package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.theater;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
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
        theater.setWebsite(theaterData.getWebsite());
        theater.setUniqueCode(theaterData.getUniqueCode());
        theater.setRecipientCode(theaterData.getRecipientCode());

        return theater;

    }

}
