package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Unemployed;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;

import java.util.Optional;

public interface UnemployedService {

    Work addUnemployed(Unemployed unemployed);

    //TODO UPDATE

    Optional<Unemployed> deleteUnemployed(Long id);

}
