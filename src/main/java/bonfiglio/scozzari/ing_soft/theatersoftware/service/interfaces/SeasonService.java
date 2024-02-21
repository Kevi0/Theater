package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Season;

import java.util.Optional;
import java.util.Set;

public interface SeasonService {

    void addSeason(Season season) throws InvalidDataException, SeasonAlreadyExistException;

    void updateSeason(Long id, Season season) throws InvalidDataException, IllegalAccessException, SeasonNotFoundException;

    Optional<Season> deleteSeason(Long id) throws SeasonAlreadyDeletedException, SeasonNotFoundException;

    Set<Optional<Season>> getAllSeasons();

    Long getSeasonIdByTitle(String title) throws SeasonNotFoundException;

}
