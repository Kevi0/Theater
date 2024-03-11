package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Opera;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Season;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Venue;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.OperaRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.SeasonRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.VenueRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.OperaService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.OperaRegistrationValidator;
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
public class OperaServiceImpl implements OperaService {

    private final VenueRepository venueRepository;

    private final SeasonRepository seasonRepository;

    private final OperaRepository operaRepository;

    private final OperaRegistrationValidator validator;

    @Override
    public void addOpera(
            Opera opera,
            Long idVenue,
            Set<Long> idSeasons
    ) throws VenueNotFoundException, SeasonNotFoundException {

        if (!operaRepository.findOperaById(opera.getId())) {

            // validator.validate(opera);

            var operaToInsert = Opera.builder()
                    .title(opera.getTitle())
                    .startDate(opera.getStartDate())
                    .startRehearsal(opera.getStartRehearsal())
                    .build();
            operaToInsert.setCreatedAt(LocalDateTime.now());

            Venue venue = venueRepository.findById(idVenue)
                    .orElseThrow(() -> new VenueNotFoundException("Venue not found with ID: " + idVenue));
            operaToInsert.setVenue(venue);

            for (Long idSeason : idSeasons) {
                Season season = seasonRepository.findById(idSeason)
                        .orElseThrow(() -> new SeasonNotFoundException("Season not found with ID: " + idSeason));
                Set<Season> seasons = opera.getSeasons() != null ? opera.getSeasons() : new HashSet<>();
                seasons.add(season);
                season.getOperas().add(operaToInsert);
                operaToInsert.setSeasons(seasons);
            }

            operaRepository.save(operaToInsert);

        }

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
    public Set<Optional<Opera>> getAllOperas() {
        return new HashSet<>(operaRepository.findAllOperas());
    }

    @Override
    public Long getOperaIdByTitle(String title) throws OperaNotFoundException {
        return operaRepository.findOperaByTitle(title)
                .map(Opera::getId)
                .orElseThrow(() -> new OperaNotFoundException("Error when getting the opera id by title"));
    }
}
