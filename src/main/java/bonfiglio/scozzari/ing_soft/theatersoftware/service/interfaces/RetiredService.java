package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Retired;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;

import java.util.Optional;

public interface RetiredService {

    Work addRetired(Retired retired);

    //TODO UPDATE

    Optional<Retired> deleteRetired(Long id);

}
