package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.artistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.subRegistrationArtistDTO.SubTemporaryWorkDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.TemporaryWork;
import org.springframework.stereotype.Component;

@Component
public class SubTemporaryWorkMapper {

    public TemporaryWork subTemporaryWorkDTOToTemporaryWork(
            SubTemporaryWorkDTO subTemporaryWorkDTO
    ){
        if(subTemporaryWorkDTO == null){
            return null;
        }

        TemporaryWork temporaryWork = new TemporaryWork();

        temporaryWork.setEmployer(subTemporaryWorkDTO.getEmployer());
        temporaryWork.setDeadline(subTemporaryWorkDTO.getDeadline());
        temporaryWork.setIsRevenueOver5000(subTemporaryWorkDTO.getIsRevenueOver5000());

        return temporaryWork;

    }

}
