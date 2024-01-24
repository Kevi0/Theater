package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.AgencyAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.AgencyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;

import java.util.Optional;
import java.util.Set;

public interface AgencyService {

    void addAgency(Agency agency) throws Exception;

    void updateAgency(Long id, Agency agency) throws Exception;

    Optional<Agency> deleteAgency(Long id) throws AgencyNotFoundException, AgencyAlreadyDeletedException;

    Set<Optional<Agency>> getAllAgencies();

    Long getAgencyIdByName(String name);

}
