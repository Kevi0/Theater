package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyService {

    Optional<Agency> addAgency(Agency agency) throws Exception;

    //TODO UPDATE

    Optional<Agency> updateAgency(Long id, Agency agency) throws Exception;

    Optional<Agency> deleteAgency(Long id);

    List<Optional<Agency>> getAllAgencies();

    Long getAgencyIdByName(String name);

}
