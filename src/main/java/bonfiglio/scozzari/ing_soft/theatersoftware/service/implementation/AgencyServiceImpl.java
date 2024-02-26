package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.AgencyRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserAgency;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.AgencyRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserAgencyRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.AgencyService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.AgencyRegistrationValidator;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("All")
@Service
@Transactional
@AllArgsConstructor
public class AgencyServiceImpl implements AgencyService {

    private final AgencyRepository agencyRepository;
    private final UserRepository userRepository;
    private final UserAgencyRepository userAgencyRepository;

    private final AgencyRegistrationValidator validator;

    @Override
    public void addAgency(Agency agency, Set<Long> idUsers) throws AgencyAlreadyExistException, InvalidDataException, UserNotFoundException {

        if (!agencyRepository.findByEmail(agency.getEmail())){

            validator.validate(agency);

            var agencyToInsert = Agency.builder()
                            .name(agency.getName())
                            .email(agency.getEmail())
                            .pec(agency.getPec())
                            .tel1(agency.getTel1())
                            .tel2(agency.getTel2())
                            .website(agency.getWebsite())
                            .build();
            agencyToInsert.setCreatedAt(LocalDateTime.now());

            for (Long idUser : idUsers) {
                User user = userRepository.findById(idUser)
                        .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + idUser));
                UserAgency userAgency = UserAgency.builder()
                        .agency(agencyToInsert)
                        .user(user)
                        .role(AgencyRoles.ADMIN)
                        .build();
                userAgencyRepository.save(userAgency);
            }

            //TODO SEND EMAIL
            agencyRepository.save(agencyToInsert);

        } else if (agencyRepository.findByEmailAndDeletedAtIsNull(agency.getEmail())) {

            Optional<Agency> agencyToUpdate = agencyRepository.findAgencyByEmail(agency.getEmail());
            Agency existingAgency = agencyToUpdate.get();
            existingAgency.setCreatedAt(LocalDateTime.now());
            existingAgency.setDeletedAt(null);
            //TODO SEND EMAIL
            agencyRepository.save(existingAgency);

        } else {
            throw new AgencyAlreadyExistException("Error when entering the agency");
        }

    }

    @Override
    public void updateAgency(Long id, Agency agency) throws IllegalAccessException, AgencyNotFoundException, InvalidDataException {

        Optional<Agency> agencyToUpdate = agencyRepository.findById(id);

        if (agencyToUpdate.isPresent() && (!agencyRepository.checkIfAgencyIsDeleted(id))) {

            validator.validate(agency);
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
    public Long getAgencyIdByName(String name) throws AgencyNotFoundException {
        return agencyRepository.findAgencyByName(name)
                .map(Agency::getId)
                .orElseThrow(() -> new AgencyNotFoundException("Error when getting the agency id by name"));
    }
}
