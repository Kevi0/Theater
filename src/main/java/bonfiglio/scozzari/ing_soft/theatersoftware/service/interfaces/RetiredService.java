package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Retired;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;

import java.util.Optional;

public interface RetiredService {

    Work addRetired(Retired retired);

    //TODO UPDATE

    Optional<Retired> deleteRetired(Long id);

}
