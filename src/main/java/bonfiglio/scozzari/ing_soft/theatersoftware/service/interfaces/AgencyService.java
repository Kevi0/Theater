package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;

import java.util.Optional;

public interface AgencyService {

    Optional<Agency> addAgency(Agency agency) throws Exception;

    //TODO UPDATE

    Optional<Agency> deleteAgency(Long id);

}
