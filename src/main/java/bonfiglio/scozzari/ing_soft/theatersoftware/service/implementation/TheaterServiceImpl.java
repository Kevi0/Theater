package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater.TheaterSummaryDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.TheaterRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.DataAccessServiceException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UnregisteredUserException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserTheater;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.TheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserTheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.TheaterService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.TheaterRegistrationValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("All")
@Service
@Transactional
@AllArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;
    private final UserRepository userRepository;
    private final UserTheaterRepository userTheaterRepository;

    private final TheaterRegistrationValidator validator;


    @Override
    public void addTheater(Theater theater, Set<Long> idUsers)

            throws InvalidDataException, TheaterAlreadyExistException, UserNotFoundException,
            UnregisteredUserException, UnregisteredTheaterException, DuplicateNameException,
            DuplicateTelException, DuplicateEmailException, DuplicatePecException,
            DuplicateUniqueCodeException, DuplicateRecipientCodeException {

        if (!(theaterRepository.findByIva(theater.getIva()).isPresent())) {

            if (theaterRepository.findByName(theater.getName()).isPresent()) {
                throw new DuplicateNameException("Errore durante la registrazione del teatro, controlla il nome inserito!");
            }

            if (theaterRepository.findByTel(theater.getTel()).isPresent()) {
                throw new DuplicateTelException("Errore durante la registrazione del teatro, controlla il telefono inserito!");
            }

            if (theaterRepository.findByEmail(theater.getEmail()).isPresent()) {
                throw new DuplicateEmailException("Errore durante la registrazione del teatro, controlla l'email inserita!");
            }

            /*if (theaterRepository.findByPec(theater.getPec()).isPresent()) {
                throw new DuplicatePecException("Errore durante la registrazione del teatro, controlla la pec inserita!");
            }*/

            if (theaterRepository.findByUniqueCode(theater.getUniqueCode()).isPresent()) {
                throw new DuplicateUniqueCodeException("Errore durante la registrazione del teatro, controlla il codice univoco inserito!");
            }

            if (theaterRepository.findByRecipientCode(theater.getRecipientCode()).isPresent()) {
                throw new DuplicateRecipientCodeException("Errore durante la registrazione del teatro, controlla il codice destinatario inserito!");
            }

            validator.validate(theater);

            var theaterToInsert = Theater.builder()
                    .name(theater.getName())
                    .city(theater.getCity())
                    .tel(theater.getTel())
                    .email(theater.getEmail())
                    .pec(theater.getPec())
                    .website(theater.getWebsite())
                    .iva(theater.getIva())
                    .uniqueCode(theater.getUniqueCode())
                    .recipientCode(theater.getRecipientCode())
                    .build();
            theaterToInsert.setCreatedAt(LocalDateTime.now());

            for (Long idUser : idUsers) {

                User user = userRepository.findById(idUser)
                        .orElseThrow(() -> new UserNotFoundException("Utenza non trovata!"));

                UserTheater userTheater = UserTheater.builder()
                        .theater(theaterToInsert)
                        .user(user)
                        .role(TheaterRoles.ADMIN)
                        .build();
                try {
                    userTheaterRepository.save(userTheater);
                } catch (ConstraintViolationException | DataIntegrityViolationException e) {
                    throw new TheaterAlreadyExistException("Errore durante la registrazione, teatro non registrato!");
                } catch (TransientObjectException | LockAcquisitionException e) {
                    throw new UnregisteredTheaterException("Errore durante la registrazione, teatro non registrato!");
                }
            }

            try {
                theaterRepository.save(theaterToInsert);
            } catch (ConstraintViolationException | DataIntegrityViolationException e) {
                throw new TheaterAlreadyExistException("Errore durante la registrazione, teatro non registrato!");
            } catch (TransientObjectException | LockAcquisitionException e) {
                throw new UnregisteredTheaterException("Errore durante la registrazione, teatro non registrato!");
            }

            //TODO SEND EMAIL


        } else {
            throw new TheaterAlreadyExistException("Errore durante la registrazione, teatro non registrato!");
        }

    }

    @Override
    public void updateTheater(Long id, Theater theater) throws InvalidDataException, IllegalAccessException, TheaterNotFoundException {

        Optional<Theater> theaterToUpdate = theaterRepository.findById(id);

        if (theaterToUpdate.isPresent()) {

            validator.validate(theater);
            Theater existingTheater = theaterToUpdate.get();
            ObjectUpdater<Theater> theaterUpdater = new ObjectUpdater<>();
            theaterRepository.save(theaterUpdater.updateObject(existingTheater, theater));

        } else {
            throw new TheaterNotFoundException("Errore durante l'aggiornamento del teatro!");
        }

    }

    @Override
    public Optional<Theater> deleteTheater(Long id) throws TheaterNotFoundException, TheaterAlreadyDeletedException {

        Optional<Theater> theaterToDelete = theaterRepository.findById(id);

        if (theaterToDelete.isPresent()) {

            Theater existingTheater = theaterToDelete.get();
            theaterRepository.delete(existingTheater);

        } else {
            throw new TheaterNotFoundException("Errore durante l'eliminazione del teatro!");
        }

        return theaterToDelete;

    }

    @Override
    public List<TheaterSummaryDTO> getAllTheaters() throws DataAccessServiceException {

        try {
            List<Theater> theaters = theaterRepository.findAll();
            return theaters.stream()
                    .map(TheaterSummaryDTO::new)
                    .toList();
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante il recupero dei teatri!");
        }

    }

    @Override
    public Long getTheaterIdByName(String name) throws TheaterNotFoundException {
        return theaterRepository.findByName(name)
                .map(Theater::getId)
                .orElseThrow(() -> new TheaterNotFoundException("Errore durante il recupero dell'id del teatro!"));
    }
}
