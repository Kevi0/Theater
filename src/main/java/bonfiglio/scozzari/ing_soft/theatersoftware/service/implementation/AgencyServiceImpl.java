package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.AgencyRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.AgencyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    @Override
    public Optional<Agency> addAgency(Agency agency) throws Exception {
        if(agencyRepository.findAgencyByName(agency.getName()).isEmpty()){
            return Optional.of(agencyRepository.save(agency));
        } else {
            throw new Exception(); //TODO Custom AgencyNotFoundException
        }
    }

    @Override
    public Optional<Agency> deleteAgency(Long id) {
        return Optional.empty();
    }
}
