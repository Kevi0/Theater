package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Season;

import java.util.Optional;

public interface SeasonService {

    Optional<Season> addSeason(Season season, Long idTheater) throws Exception;

    //TODO UPDATE

    Optional<Season> deleteSeason(Long id);

}
