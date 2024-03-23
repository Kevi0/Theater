package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
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
import java.util.List;
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
    public void updateOpera(Long id, Opera opera) throws IllegalAccessException, OperaNotFoundException, InvalidDataException {

        Optional<Opera> operaToUpdate = operaRepository.findById(id);

        if (operaToUpdate.isPresent()) {

            validator.validate(opera);
            Opera existingOpera = operaToUpdate.get();
            ObjectUpdater<Opera> operaUpdater = new ObjectUpdater<>();
            operaRepository.save(operaUpdater.updateObject(existingOpera, opera));

        } else {
            throw new OperaNotFoundException("Error when updating the opera");
        }

    }

    @Override
    public Optional<Opera> deleteOpera(Long id) throws OperaNotFoundException {
        Optional<Opera> operaToDelete = operaRepository.findById(id);

        if (operaToDelete.isPresent()) {

            Opera existingOpera = operaToDelete.get();
            operaRepository.delete(existingOpera);

        } else {
            throw new OperaNotFoundException("Error when deleting the theater");
        }

        return operaToDelete;
    }

    @Override
    public List<Opera> getAllOperas() {
        try {
            return operaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error when getting all the operas");
        }
    }

    @Override
    public Long getOperaIdByTitle(String title) throws OperaNotFoundException {
        return operaRepository.findOperaByTitle(title)
                .map(Opera::getId)
                .orElseThrow(() -> new OperaNotFoundException("Error when getting the opera id by title"));
    }
}
