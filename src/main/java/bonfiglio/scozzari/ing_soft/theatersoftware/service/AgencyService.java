package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;

import java.util.Optional;
import java.util.Set;

public interface AgencyService {

    void addAgency(Agency agency, Set<Long> idUser) throws AgencyAlreadyExistException, InvalidDataException, UserNotFoundException;

    void updateAgency(Long id, Agency agency) throws IllegalAccessException, AgencyNotFoundException, InvalidDataException;

    Optional<Agency> deleteAgency(Long id) throws AgencyNotFoundException, AgencyAlreadyDeletedException;

    Set<Optional<Agency>> getAllAgencies();

    Long getAgencyIdByName(String name) throws AgencyNotFoundException;

}
