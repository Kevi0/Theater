package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.PermanentWork;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;

import java.util.Optional;

public interface PermanentWorkService {

    Work addPermanentWork(PermanentWork permanentWork);

    //TODO UPDATE

    Optional<PermanentWork> deletePermanentWork(Long id);
}
