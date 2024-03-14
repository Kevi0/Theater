package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.theater;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater.TheaterUpdateRequestDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import org.springframework.stereotype.Component;

@Component
public class TheaterUpdateRequestMapper {

    public Theater theaterDTOToTheater(InputDTO theaterDTO) {
        if (!(theaterDTO instanceof TheaterUpdateRequestDTO updateData))
            throw new ClassCastException("Errore di conversione");
        else {
            Theater theater = new Theater();

            theater.setName(updateData.getName());
            theater.setCity(updateData.getCity());
            theater.setTel(updateData.getTel());
            theater.setEmail(updateData.getEmail());
            theater.setPec(updateData.getPec());
            theater.setWebsite(updateData.getWebsite());
            theater.setIva(updateData.getIva());
            theater.setUniqueCode(updateData.getUniqueCode());
            theater.setRecipientCode(updateData.getRecipientCode());

            return theater;
        }
    }

}
