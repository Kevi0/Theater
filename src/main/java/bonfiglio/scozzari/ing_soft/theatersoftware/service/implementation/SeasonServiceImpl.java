package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Season;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.SeasonRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.TheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.SeasonService;
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
public class SeasonServiceImpl implements SeasonService {

    private final TheaterRepository theaterRepository;

    private final SeasonRepository seasonRepository;

    @Override
    public Optional<Season> addSeason(Season season) throws Exception {
        if (seasonRepository.findSeasonByTitle(season.getTitle()).isEmpty()){
            season.setCreatedAt(LocalDateTime.now());
            return Optional.of(seasonRepository.save(season));
        } else {
            throw new Exception(); //TODO Custom SeasonAlreadyExistException
        }
    }

    @Override
    public Optional<Season> updateSeason(Long id, Season season) throws Exception {
        Optional<Season> seasonToUpdate = seasonRepository.findById(id);

        if (seasonToUpdate.isPresent()){
            Season existingSeason = seasonToUpdate.get();

            Field[] fields = season.getClass().getDeclaredFields();

            for(Field field : fields){
                field.setAccessible(true);

                Object fieldValue = field.get(season);

                if (fieldValue != null && !fieldValue.equals(field.get(existingSeason))){
                    field.set(existingSeason, fieldValue);
                }
            }
            existingSeason.setUpdatedAt(LocalDateTime.now());
            return Optional.of(seasonRepository.save(existingSeason));
        } else {
            throw new Exception("Season not found!"); //TODO Custom SeasonNotFoundException
        }
    }

    @Override
    public Optional<Season> deleteSeason(Long id) {
        Optional<Season> seasonToDelete = seasonRepository.findById(id);

        if (seasonToDelete.isPresent()){
            Season existingSeason = seasonToDelete.get();

            if (existingSeason.getDeletedAt() == null){
                seasonRepository.deleteSeasonById(id);
                return Optional.of(seasonRepository.save(existingSeason));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Optional<Season>> getAllSeasons() {
        return seasonRepository.findAllSeasons().stream().toList();
    }

    @Override
    public Long getSeasonIdByTitle(String title) {
        return seasonRepository.findSeasonByTitle(title).get().getId();
    }
}
