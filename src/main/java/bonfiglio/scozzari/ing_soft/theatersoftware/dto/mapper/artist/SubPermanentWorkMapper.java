package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubPermanentWorkDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.PermanentWork;
import org.springframework.stereotype.Component;

@Component
public class SubPermanentWorkMapper {

    public PermanentWork subPermanentWorkDTOToPermanentWork(
            SubPermanentWorkDTO subPermanentWorkDTO
    ){
        if (subPermanentWorkDTO == null) {
            return null;
        }

        PermanentWork permanentWork = new PermanentWork();

        permanentWork.setEmployer(subPermanentWorkDTO.getEmployer());
        permanentWork.setIsRevenueOver5000(subPermanentWorkDTO.getRevenue());

        return permanentWork;
    }

}
