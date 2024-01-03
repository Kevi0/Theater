package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Opera;

import java.util.List;
import java.util.Optional;

public interface OperaService {

    Optional<Opera> addOpera(Opera opera, Long idVenue, Long idSeason) throws Exception;

    Optional<Opera> addOpera(Opera opera) throws Exception;

    //TODO UPDATE

    Optional<Opera> updateOpera(Long id, Opera opera) throws Exception;

    Optional<Opera> deleteOpera(Long id);

    List<Optional<Opera>> getAllOperas();

    Long getOperaIdByTitle(String title);

}
