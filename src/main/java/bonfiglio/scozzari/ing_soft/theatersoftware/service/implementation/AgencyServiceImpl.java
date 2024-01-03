package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.AgencyRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.AgencyService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;

    @Override
    public Optional<Agency> addAgency(Agency agency) throws Exception {
        if(agencyRepository.findAgencyByName(agency.getName()).isEmpty()){
            agency.setCreatedAt(LocalDateTime.now());
            return Optional.of(agencyRepository.save(agency));
        } else {
            throw new Exception(); //TODO Custom AgencyNotFoundException
        }
    }

    @Override
    public Optional<Agency> updateAgency(Long id, Agency agency) throws Exception {
        Optional<Agency> agencyToUpdate = agencyRepository.findById(id);

        if (agencyToUpdate.isPresent()){
            Agency existingAgency = agencyToUpdate.get();

            Field[] fields = agency.getClass().getDeclaredFields();

            for(Field field : fields){
                field.setAccessible(true);

                Object fieldValue = field.get(agency);

                if (fieldValue != null && !fieldValue.equals(field.get(existingAgency))){
                    field.set(existingAgency, fieldValue);
                }
            }
            return Optional.of(agencyRepository.save(existingAgency));
        } else {
            throw new Exception("Agency not found!"); //TODO Custom AgencyNotFoundException
        }
    }

    @Override
    public Optional<Agency> deleteAgency(Long id) {
        Optional<Agency> agencyToDelete = agencyRepository.findById(id);

        if (agencyToDelete.isPresent()){
            Agency existingAgency = agencyToDelete.get();

            if (existingAgency.getDeletedAt() == null){
                agencyRepository.deleteAgencyById(id);
                return Optional.of(agencyRepository.save(existingAgency));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Optional<Agency>> getAllAgencies() {
        return agencyRepository.findAllAgencies().stream().toList();
    }

    @Override
    public Long getAgencyIdByName(String name) {
        return agencyRepository.findAgencyByName(name).get().getId();
    }
}
