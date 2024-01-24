package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.AgencyAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.AgencyAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.AgencyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.AgencyRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.AgencyService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    @Override
    public void addAgency(Agency agency) throws AgencyAlreadyExistException {

        if (agencyRepository.findAgencyByName(agency.getName()).isEmpty()){
            var agencyToInsert = Agency.builder()
                            .name(agency.getName())
                            .email(agency.getEmail())
                            .pec(agency.getPec())
                            .tel1(agency.getTel1())
                            .tel2(agency.getTel2())
                            .website(agency.getWebsite())
                            .build();
            agencyToInsert.setCreatedAt(LocalDateTime.now());
            agencyRepository.save(agencyToInsert);

        } else {
            throw new AgencyAlreadyExistException("Error when entering the agency");
        }

    }

    @Override
    public void updateAgency(Long id, Agency agency) throws IllegalAccessException, AgencyNotFoundException {

        Optional<Agency> agencyToUpdate = agencyRepository.findById(id);

        if (agencyToUpdate.isPresent()) {
            Agency existingAgency = agencyToUpdate.get();
            ObjectUpdater<Agency> agencyUpdater = new ObjectUpdater<>();
            agencyRepository.save(agencyUpdater.updateObject(existingAgency, agency));

        } else {
            throw new AgencyNotFoundException("Error when updating the agency");
        }

    }

    @Override
    public Optional<Agency> deleteAgency(Long id) throws AgencyNotFoundException, AgencyAlreadyDeletedException {

        Optional<Agency> agencyToDelete = agencyRepository.findById(id);

        if (agencyToDelete.isPresent()) {
            Agency existingAgency = agencyToDelete.get();

            if (existingAgency.getDeletedAt() == null) {
                agencyRepository.deleteAgencyById(id);
                return Optional.of(existingAgency);

            } else {
                throw new AgencyAlreadyDeletedException("Error when deleting the agency");
            }

        } else {
            throw new AgencyNotFoundException("Error when deleting the agency");
        }
    }

    @Override
    public Set<Optional<Agency>> getAllAgencies() {
        return new HashSet<>(agencyRepository.findAllAgencies());
    }

    @Override
    public Long getAgencyIdByName(String name) {
        return agencyRepository.findAgencyByName(name).get().getId();
    }
}
