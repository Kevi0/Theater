package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.agency;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.agency.AgencyDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import org.springframework.stereotype.Component;

@Component
public class AgencyMapper {

    public Agency agencyDTOToAgency(InputDTO agencyDTO){
        if(!(agencyDTO instanceof AgencyDTO agencyData))
            return null;
        else {
            Agency agency = new Agency();

            agency.setName(agencyData.getName());
            agency.setEmail(agencyData.getEmail());
            agency.setPec(agencyData.getPec());
            agency.setTel1(agencyData.getTel1());
            agency.setTel2(agencyData.getTel2());
            agency.setWebsite(agencyData.getWebsite());
            return agency;
        }
    }

}
