package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Opera;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OperaService {

    void addOpera(Opera opera, Long idVenue, Set<Long> idSeasons) throws VenueNotFoundException, SeasonNotFoundException;

    void addOpera(Opera opera) throws OperaAlreadyExistException, InvalidDataException;

    void updateOpera(Long id, Opera opera) throws IllegalAccessException, OperaNotFoundException, InvalidDataException;

    Optional<Opera> deleteOpera(Long id) throws OperaAlreadyDeletedException, OperaNotFoundException;

    Set<Optional<Opera>> getAllOperas();

    Long getOperaIdByTitle(String title) throws OperaNotFoundException;

}
