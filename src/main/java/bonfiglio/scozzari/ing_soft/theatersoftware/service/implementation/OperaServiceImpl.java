package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Opera;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.OperaRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.SeasonRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.VenueRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.OperaService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.OperaRegistrationValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OperaServiceImpl implements OperaService {

    private final VenueRepository venueRepository;

    private final SeasonRepository seasonRepository;

    private final OperaRepository operaRepository;

    private final OperaRegistrationValidator validator;

    @Override
    public void addOpera(
            Opera opera,
            Long idVenue,
            Long idSeason
    ) {
        /*Optional<Venue> venue = venueRepository.findById(idVenue);
        Optional<Season> season = seasonRepository.findById(idSeason);

        if(venue.isEmpty())
            throw new Exception("Venue not found!"); //TODO Custom VenueNotFoundException

        if(season.isEmpty())
            throw new Exception("Season not found!"); //TODO Custom SeasonNotFoundException

        opera.setVenue(venue.get());
        opera.getSeasons().add(season.get());
        return Optional.of(operaRepository.save(opera));*/
    }

    @Override
    public void addOpera(Opera opera) throws OperaAlreadyExistException, InvalidDataException {

        if (operaRepository.findById(opera.getId()).isEmpty()) {

            validator.validate(opera);

            var operaToInsert = Opera.builder()
                    .title(opera.getTitle())
                    .startDate(opera.getStartDate())
                    .startRehearsal(opera.getStartRehearsal())
                    .build();
            opera.setCreatedAt(LocalDateTime.now());
            operaRepository.save(operaToInsert);

        } else {
            throw new OperaAlreadyExistException("Error when entering the opera");
        }

    }

    @Override
    public void updateOpera(Long id, Opera opera) throws IllegalAccessException, OperaNotFoundException, InvalidDataException {

        Optional<Opera> operaToUpdate = operaRepository.findById(id);

        if (operaToUpdate.isPresent() && (operaRepository.checkIfOperaIsDeleted(id))) {

            validator.validate(opera);
            Opera existingOpera = operaToUpdate.get();
            ObjectUpdater<Opera> operaUpdater = new ObjectUpdater<>();
            operaRepository.save(operaUpdater.updateObject(existingOpera, opera));

        } else {
            throw new OperaNotFoundException("Error when updating the opera");
        }

    }

    @Override
    public Optional<Opera> deleteOpera(Long id) throws OperaAlreadyDeletedException, OperaNotFoundException {
        Optional<Opera> operaToDelete = operaRepository.findById(id);

        if (operaToDelete.isPresent()) {
            Opera existingOpera = operaToDelete.get();

            if (existingOpera.getDeletedAt() == null) {
                operaRepository.deleteOperaById(id);
                return Optional.of(operaRepository.save(existingOpera));
            } else {
                throw new OperaAlreadyDeletedException("Error when deleting the theater");
            }
        } else {
            throw new OperaNotFoundException("Error when deleting the theater");
        }
    }

    @Override
    public List<Optional<Opera>> getAllOperas() {
        return operaRepository.findAllOperas().stream().toList();
    }

    @Override
    public Long getOperaIdByTitle(String title) {
        return operaRepository.findOperaByTitle(title).get().getId();
    }
}
