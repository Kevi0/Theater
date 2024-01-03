package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Opera;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Season;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Venue;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.OperaRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.SeasonRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.VenueRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.OperaService;
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
public class OperaServiceImpl implements OperaService {

    private final VenueRepository venueRepository;

    private final SeasonRepository seasonRepository;

    private final OperaRepository operaRepository;

    @Override
    public Optional<Opera> addOpera(
            Opera opera,
            Long idVenue,
            Long idSeason
    ) throws Exception {
        Optional<Venue> venue = venueRepository.findById(idVenue);
        Optional<Season> season = seasonRepository.findById(idSeason);

        if(venue.isEmpty())
            throw new Exception("Venue not found!"); //TODO Custom VenueNotFoundException

        if(season.isEmpty())
            throw new Exception("Season not found!"); //TODO Custom SeasonNotFoundException

        opera.setVenue(venue.get());
        opera.getSeasons().add(season.get());
        return Optional.of(operaRepository.save(opera));
    }

    @Override
    public Optional<Opera> addOpera(Opera opera) throws Exception {
        if (operaRepository.findOperaByTitle(opera.getTitle()).isEmpty()){
            opera.setCreatedAt(LocalDateTime.now());
            return Optional.of(operaRepository.save(opera));
        } else {
            throw new Exception(); //TODO Custom OperaNotFoundException
        }
    }

    @Override
    public Optional<Opera> updateOpera(Long id, Opera opera) throws Exception {
        Optional<Opera> operaToUpdate = operaRepository.findById(id);

        if (operaToUpdate.isPresent()){
            Opera existingOpera = operaToUpdate.get();

            Field[] fields = opera.getClass().getDeclaredFields();

            for(Field field : fields){
                field.setAccessible(true);

                Object fieldValue = field.get(opera);

                if (fieldValue != null && !fieldValue.equals(field.get(existingOpera))){
                    field.set(existingOpera, fieldValue);
                }
            }
            existingOpera.setUpdatedAt(LocalDateTime.now());
            return Optional.of(operaRepository.save(existingOpera));
        } else {
            throw new Exception("Opera not found!"); //TODO Custom OperaNotFoundException
        }
    }

    @Override
    public Optional<Opera> deleteOpera(Long id) {
        Optional<Opera> operaToDelete = operaRepository.findById(id);

        if (operaToDelete.isPresent()) {
            Opera existingOpera = operaToDelete.get();

            // Verifica se l'opera è già stata cancellata
            if (existingOpera.getDeletedAt() == null) {
                operaRepository.deleteOperaById(id);
                return Optional.of(operaRepository.save(existingOpera));
            } else {
                // L'opera è già stata cancellata
                return Optional.empty();
            }
        } else {
            // L'opera non è stata trovata
            return Optional.empty();
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
