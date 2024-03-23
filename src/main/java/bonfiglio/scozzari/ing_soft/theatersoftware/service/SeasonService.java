package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.DataAccessServiceException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Season;

import java.util.List;
import java.util.Optional;

public interface SeasonService {

    void addSeason(Season season, Long idTheater) throws InvalidDataException, SeasonAlreadyExistException, UserNotFoundException, TheaterNotFoundException;

    void updateSeason(Long id, Season season) throws InvalidDataException, IllegalAccessException, SeasonNotFoundException;

    Optional<Season> deleteSeason(Long id) throws SeasonAlreadyDeletedException, SeasonNotFoundException;

    List<Season> getAllSeasons() throws DataAccessServiceException;

    Long getSeasonIdByTitle(String title) throws SeasonNotFoundException;

}
