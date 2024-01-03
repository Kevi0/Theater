package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Season;

import java.util.List;
import java.util.Optional;

public interface SeasonService {

    Optional<Season> addSeason(Season season) throws Exception;

    //TODO UPDATE

    Optional<Season> updateSeason(Long id, Season season) throws Exception;

    Optional<Season> deleteSeason(Long id);

    List<Optional<Season>> getAllSeasons();

    Long getSeasonIdByTitle(String title);

}
