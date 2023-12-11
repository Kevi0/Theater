package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Opera;

import java.util.Optional;

public interface OperaService {

    Optional<Opera> addOpera(Opera opera, Long idVenue, Long idSeason) throws Exception;

    //TODO UPDATE

    Optional<Opera> deleteOpera(Long id);

}
