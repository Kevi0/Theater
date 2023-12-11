package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.TemporaryWork;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;

import java.util.Optional;

public interface TemporaryWorkService {

    Work addTemporaryWork(TemporaryWork temporaryWork);

    //TODO UPDATE

    Optional<TemporaryWork> deleteTemporaryWork(Long id);

}
