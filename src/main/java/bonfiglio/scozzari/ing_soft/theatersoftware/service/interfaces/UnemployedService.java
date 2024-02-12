package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Unemployed;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;

import java.util.Optional;

public interface UnemployedService {

    Work addUnemployed(Unemployed unemployed);

    //TODO UPDATE

    Optional<Unemployed> deleteUnemployed(Long id);

}
