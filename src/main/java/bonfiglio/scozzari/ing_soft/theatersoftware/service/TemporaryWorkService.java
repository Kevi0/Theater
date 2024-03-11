package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.TemporaryWork;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;

import java.util.Optional;

public interface TemporaryWorkService {

    Work addTemporaryWork(TemporaryWork temporaryWork);

    //TODO UPDATE

    Optional<TemporaryWork> deleteTemporaryWork(Long id);

}
