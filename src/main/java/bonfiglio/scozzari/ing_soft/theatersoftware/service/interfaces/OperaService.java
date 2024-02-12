package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Opera;

import java.util.List;
import java.util.Optional;

public interface OperaService {

    void addOpera(Opera opera, Long idVenue, Long idSeason);

    void addOpera(Opera opera) throws OperaAlreadyExistException, InvalidDataException;

    void updateOpera(Long id, Opera opera) throws IllegalAccessException, OperaNotFoundException, InvalidDataException;

    Optional<Opera> deleteOpera(Long id) throws OperaAlreadyDeletedException, OperaNotFoundException;

    List<Optional<Opera>> getAllOperas();

    Long getOperaIdByTitle(String title);

}
