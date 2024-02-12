package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Play;

import java.util.Optional;

public interface PlayService {

    Optional<Play> addPlay(Play play, Long idOpera);

    //TODO UPDATE

    Optional<Play> deletePlay(Long id);

}
