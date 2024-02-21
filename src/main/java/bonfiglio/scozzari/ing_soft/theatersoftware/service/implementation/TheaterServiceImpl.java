package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.TheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.TheaterService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.TheaterRegistrationValidator;
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
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;

    private final TheaterRegistrationValidator validator;


    @Override
    public void addTheater(Theater theater) throws InvalidDataException, TheaterAlreadyExistException {

        if (theaterRepository.findTheaterByName(theater.getName()).isEmpty()){

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
            theaterRepository.save(theaterToInsert);

        } else {
            throw new TheaterAlreadyExistException("Error when entering the theater");
        }

    }

    @Override
    public void updateTheater(Long id, Theater theater) throws InvalidDataException, IllegalAccessException, TheaterNotFoundException {

        Optional<Theater> theaterToUpdate = theaterRepository.findById(id);

        if (theaterToUpdate.isPresent() && (!theaterRepository.checkIfTheaterIsDeleted(id))) {

            validator.validate(theater);
            Theater existingTheater = theaterToUpdate.get();
            ObjectUpdater<Theater> theaterUpdater = new ObjectUpdater<>();
            theaterRepository.save(theaterUpdater.updateObject(existingTheater, theater));

        } else {
            throw new TheaterNotFoundException("Error when updating the theater");
        }

    }

    @Override
    public Optional<Theater> deleteTheater(Long id) throws TheaterNotFoundException, TheaterAlreadyDeletedException {

        Optional<Theater> theaterToDelete = theaterRepository.findById(id);

        if (theaterToDelete.isPresent()) {
            Theater existingTheater = theaterToDelete.get();

            if (existingTheater.getDeletedAt() == null) {
                theaterRepository.deleteTheaterById(id);
                return Optional.of(existingTheater);

            } else {
                throw new TheaterAlreadyDeletedException("Error when deleting the theater");
            }
        } else {
            throw new TheaterNotFoundException("Error when deleting the theater");
        }

    }

    @Override
    public Set<Optional<Theater>> getAllTheaters() {
        return new HashSet<>(theaterRepository.findAllTheaters());
    }

    @Override
    public Long getTheaterIdByName(String name) throws TheaterNotFoundException {
        return theaterRepository.findTheaterByName(name)
                .map(Theater::getId)
                .orElseThrow(() -> new TheaterNotFoundException("Error when getting the theater id by name"));
    }
}
