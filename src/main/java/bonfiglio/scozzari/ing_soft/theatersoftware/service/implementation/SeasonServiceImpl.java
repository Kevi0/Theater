package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Season;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.SeasonRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.TheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.SeasonService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.SeasonRegistrationValidator;
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
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;
    private final TheaterRepository theaterRepository;

    private final SeasonRegistrationValidator validator;

    @Override
    public void addSeason(Season season, Long idTheater) throws InvalidDataException, SeasonAlreadyExistException, TheaterNotFoundException {

        if (seasonRepository.findSeasonByTitle(season.getTitle()).isEmpty()){

            validator.validate(season);

            var seasonToInsert = Season.builder()
                    .title(season.getTitle())
                    .startDate(season.getStartDate())
                    .endDate(season.getEndDate())
                    .artisticDirectorSocialCosts(season.getArtisticDirectorSocialCosts())
                    .artisticDirectorCompensation(season.getArtisticDirectorCompensation())
                    .artisticPersonnelGrossSalary(season.getArtisticPersonnelGrossSalary())
                    .artisticPersonnelSocialCosts(season.getArtisticPersonnelSocialCosts())
                    .technicalPersonnelGrossSalary(season.getTechnicalPersonnelGrossSalary())
                    .technicalPersonnelSocialCosts(season.getTechnicalPersonnelSocialCosts())
                    .administrativePersonnelGrossSalary(season.getAdministrativePersonnelGrossSalary())
                    .administrativePersonnelSocialCosts(season.getAdministrativePersonnelSocialCosts())
                    .artisticPersonnelPerDiem(season.getArtisticPersonnelPerDiem())
                    .technicalPersonnelPerDiem(season.getTechnicalPersonnelPerDiem())
                    .projectRelatedDailyExpenses(season.getProjectRelatedDailyExpenses())
                    .travelTransportAccommodationCosts(season.getTravelTransportAccommodationCosts())
                    .build();
            seasonToInsert.setCreatedAt(LocalDateTime.now());

            Theater theater = theaterRepository.findById(idTheater)
                    .orElseThrow(() -> new TheaterNotFoundException("Theater not found with ID: " + idTheater));

            seasonToInsert.setTheater(theater);

            seasonRepository.save(seasonToInsert);

        } else {
            throw new SeasonAlreadyExistException("Error when entering the season");
        }

    }

    @Override
    public void updateSeason(Long id, Season season) throws InvalidDataException, IllegalAccessException, SeasonNotFoundException {

        Optional<Season> seasonToUpdate = seasonRepository.findById(id);

        if (seasonToUpdate.isPresent() && (!seasonRepository.checkIfSeasonIsDeleted(id))) {

            validator.validate(season);
            Season existingSeason = seasonToUpdate.get();
            ObjectUpdater<Season> updater = new ObjectUpdater<>();
            seasonRepository.save(updater.updateObject(season, existingSeason));

        } else {
            throw new SeasonNotFoundException("Error when updating the season");
        }
        
    }

    @Override
    public Optional<Season> deleteSeason(Long id) throws SeasonAlreadyDeletedException, SeasonNotFoundException {

        Optional<Season> seasonToDelete = seasonRepository.findById(id);

        if (seasonToDelete.isPresent()) {
            Season existingSeason = seasonToDelete.get();

            if (existingSeason.getDeletedAt() == null) {
                seasonRepository.deleteSeasonById(id);
                return Optional.of(existingSeason);
            } else {
                throw new SeasonAlreadyDeletedException("Error when deleting the season");
            }

        } else {
            throw new SeasonNotFoundException("Error when deleting the season");
        }

    }

    @Override
    public Set<Optional<Season>> getAllSeasons() {
        return new HashSet<>(seasonRepository.findAllSeasons());
    }

    @Override
    public Long getSeasonIdByTitle(String title) throws SeasonNotFoundException {
        return seasonRepository.findSeasonByTitle(title)
                .map(Season::getId)
                .orElseThrow(() -> new SeasonNotFoundException("Error when getting the season id by title"));
    }
}
