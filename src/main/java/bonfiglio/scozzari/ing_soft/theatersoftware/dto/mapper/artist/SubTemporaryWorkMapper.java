package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubTemporaryWorkDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.TemporaryWork;
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
