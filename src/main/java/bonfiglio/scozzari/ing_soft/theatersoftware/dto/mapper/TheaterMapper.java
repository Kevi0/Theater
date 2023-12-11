package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;
import org.springframework.stereotype.Component;

@Component
public class TheaterMapper {
    public Theater theaterDTOToTheater(InputDTO dto) {

        if (dto instanceof TheaterDTO theaterdto) {
            Theater theater = new Theater();

            theater.setName( theaterdto.getName() );
            theater.setCity( theaterdto.getCity() );
            theater.setTel( theaterdto.getTel() );
            theater.setEmail( theaterdto.getEmail() );
            theater.setIva( theaterdto.getIva() );
            theater.setPec( theaterdto.getPec() );
            theater.setTaxCode( theaterdto.getTaxCode() );
            theater.setWebSite( theaterdto.getWebSite() );
            theater.setUniqueCode( theaterdto.getUniqueCode() );
            theater.setRecipientCode( theaterdto.getRecipientCode() );

            return theater;
        }else
            throw new ClassCastException("Il DTO fornito non è un TheaterDTO");
    }
}
