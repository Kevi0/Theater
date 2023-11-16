package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.subArtistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO.SubPermanentWorkDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.PermanentWork;
import org.springframework.stereotype.Component;

@Component
public class SubPermanentWorkMapper {

    public static PermanentWork subPermanentWorkDTOToPermanentWork (SubPermanentWorkDTO subPermanentWorkDTO) {
        if (subPermanentWorkDTO == null) {
            return null;
        }

        PermanentWork permanentWork = new PermanentWork();
        permanentWork.setEmployer(subPermanentWorkDTO.getEmployer());
        permanentWork.setIsRevenueOver5000(subPermanentWorkDTO.getRevenue());

        return permanentWork;
    }
}
