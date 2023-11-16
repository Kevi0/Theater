package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.subArtistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO.SubTemporaryWorkDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.TemporaryWork;
import org.springframework.stereotype.Component;

@Component
public class SubTemporaryWorkMapper {

    public static TemporaryWork subTemporaryWorkDTOToTemporaryWork (SubTemporaryWorkDTO subTemporaryWorkDTO) {
        if (subTemporaryWorkDTO == null) {
            return null;
        }

        TemporaryWork temporaryWork = new TemporaryWork();
        temporaryWork.setEmployer(subTemporaryWorkDTO.getEmployer());
        temporaryWork.setDeadline(subTemporaryWorkDTO.getDeadline());
        temporaryWork.setIsRevenueOver5000(subTemporaryWorkDTO.getIsRevenueOver5000());

        return temporaryWork;
    }
}
