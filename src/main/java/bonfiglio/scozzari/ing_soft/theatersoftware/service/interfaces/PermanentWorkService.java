package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.PermanentWork;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;

import java.util.Optional;

public interface PermanentWorkService {

    Work addPermanentWork(PermanentWork permanentWork);

    //TODO UPDATE

    Optional<PermanentWork> deletePermanentWork(Long id);
}
