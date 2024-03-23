package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.AgencyRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.DataAccessServiceException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.DuplicateEmailException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.DuplicatePecException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.DuplicateTelException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserAgency;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.AgencyRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserAgencyRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.AgencyService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.AgencyRegistrationValidator;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public void addAgency(Agency agency, Set<Long> idUsers)

            throws AgencyAlreadyExistException, InvalidDataException, UserNotFoundException,
            DuplicateEmailException, DuplicatePecException, DuplicateTelException {

        if (!(agencyRepository.findByName(agency.getName()).isPresent())) {

            if (agencyRepository.findByEmail(agency.getEmail()).isPresent()) {
                throw new DuplicateEmailException("Errore durante la registrazione dell'agenzia, controlla l'email inserita!");
                //TODO INSERT THIS EXCEPTION IN GLOBAL EXCEPTION MANAGER BECAUSE IS USED IN OTHER SERVICES
            }

            /*if (agencyRepository.findByPec(agency.getPec()).isPresent()) {
                throw new DuplicatePecException("Errore durante la registrazione dell'agenzia, controlla la pec inserita!");
                //TODO INSERT THIS EXCEPTION IN GLOBAL EXCEPTION MANAGER BECAUSE IS USED IN OTHER SERVICES
            }*/

            if (agencyRepository.findByTel1(agency.getTel1()).isPresent()) {
                throw new DuplicateTelException("Errore durante la registrazione dell'agenzia, controlla il telefono inserito!");
                //TODO INSERT THIS EXCEPTION IN GLOBAL EXCEPTION MANAGER BECAUSE IS USED IN OTHER SERVICES
            }

            //TODO CHECK TEL2

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
                        .orElseThrow(() -> new UserNotFoundException("Errore durante la registrazione dell'agenzia, utente non trovato"));
                UserAgency userAgency = UserAgency.builder()
                        .agency(agencyToInsert)
                        .user(user)
                        .role(AgencyRoles.ADMIN)
                        .build();
                userAgencyRepository.save(userAgency);
            }

            //TODO SEND EMAIL
            agencyRepository.save(agencyToInsert);

        } else {
            throw new AgencyAlreadyExistException("Errore durante la registrazione dell'agenzia, controlla il nome inserito!");
        }

    }

    @Override
    public void updateAgency(Long id, Agency agency) throws IllegalAccessException, AgencyNotFoundException, InvalidDataException {

        Optional<Agency> agencyToUpdate = agencyRepository.findById(id);

        if (agencyToUpdate.isPresent()) {

            validator.validate(agency);
            Agency existingAgency = agencyToUpdate.get();
            ObjectUpdater<Agency> agencyUpdater = new ObjectUpdater<>();
            agencyRepository.save(agencyUpdater.updateObject(existingAgency, agency));

        } else {
            throw new AgencyNotFoundException("Errore durante l'aggiornamento dell'agenzia");
        }

    }

    @Override
    public Optional<Agency> deleteAgency(Long id) throws AgencyNotFoundException {

        Optional<Agency> agencyToDelete = agencyRepository.findById(id);

        if (agencyToDelete.isPresent()) {

            Agency existingAgency = agencyToDelete.get();
            agencyRepository.delete(existingAgency);

        } else {
            throw new AgencyNotFoundException("Errore durante l'eliminazione dell'agenzia");
        }

        return agencyToDelete;

    }

    @Override
    public List<Agency> getAllAgencies() throws DataAccessServiceException {
        try {
            return agencyRepository.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante il recupero delle agenzie");
        }
    }

    @Override
    public Long getAgencyIdByName(String name) throws AgencyNotFoundException {
        return agencyRepository.findByName(name)
                .map(Agency::getId)
                .orElseThrow(() -> new AgencyNotFoundException("Errore durante il recupero dell'id dell'agenzia"));
    }
}
