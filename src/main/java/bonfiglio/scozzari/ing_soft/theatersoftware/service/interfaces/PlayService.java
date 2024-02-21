package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.play.PlayAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.play.PlayAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.play.PlayNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Play;

import java.util.Optional;
import java.util.Set;

public interface PlayService {

    void addPlay(Play play, Long idOpera);

    void addPlay(Play play) throws PlayAlreadyExistException, InvalidDataException;

    void updatePlay(Long id, Play play) throws InvalidDataException, IllegalAccessException, PlayNotFoundException;

    Optional<Play> deletePlay(Long id) throws PlayAlreadyDeletedException, PlayNotFoundException;

    Set<Optional<Play>> getAllPlays();

    Long getPlayIdByName(String name) throws PlayNotFoundException;

}
