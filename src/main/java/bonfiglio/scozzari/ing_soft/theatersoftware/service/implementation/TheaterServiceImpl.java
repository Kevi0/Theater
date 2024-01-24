package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.TheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.TheaterService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;

    @Override
    public Optional<Theater> addTheater(Theater theater) throws Exception {
        if (theaterRepository.findTheaterByName(theater.getName()).isEmpty()){
            theater.setCreatedAt(LocalDateTime.now());
            return Optional.of(theaterRepository.save(theater));
        } else {
            throw new Exception(); //TODO Custom TheaterNotFoundException
        }
    }

    @Override
    public Theater updateTheater(Long id, Theater theater) throws Exception {
        Optional<Theater> theaterToUpdate = theaterRepository.findById(id);
        if (theaterToUpdate.isPresent()){
            Theater existingTheater = theaterToUpdate.get();
            ObjectUpdater<Theater> theaterUpdater = new ObjectUpdater<>();
            return theaterRepository.save(theaterUpdater.updateObject(existingTheater, theater));
        } else {
            throw new Exception("Theater not found!"); //TODO Custom TheaterNotFoundException
        }
    }

    @Override
    public Optional<Theater> deleteTheater(Long id) {
        /*Optional<Theater> theaterToDelete = theaterRepository.findById(id);

        if (theaterToDelete.isPresent()) {
            Theater existingTheater = theaterToDelete.get();

            // Verifica se il teatro è già stato cancellato
            if (existingTheater.getDeletedAt() == null) {
                existingTheater.setDeletedAt(LocalDateTime.now());
                return Optional.of(theaterRepository.save(existingTheater));
            } else {
                // Il teatro è già stato cancellato
                return Optional.empty();
            }
        } else {
            // Il teatro non è stato trovato
            return Optional.empty();
        }*/

        Optional<Theater> theaterToDelete = theaterRepository.findById(id);

        if (theaterToDelete.isPresent()) {
            Theater existingTheater = theaterToDelete.get();

            // Verifica se il teatro è già stato cancellato
            if (existingTheater.getDeletedAt() == null) {
                theaterRepository.deleteTheaterById(id);
                return Optional.of(existingTheater);
            } else {
                // Il teatro è già stato cancellato
                return Optional.empty();
            }
        } else {
            // Il teatro non è stato trovato
            return Optional.empty();
        }
    }


    @Override
    public List<Optional<Theater>> getAllTheaters() {
        return theaterRepository.findAllTheaters().stream().toList();
    }

    @Override
    public Long getTheaterIdByName(String name) {
        return theaterRepository.findTheaterByName(name).get().getId();
    }
}
