package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Venue;

import java.util.Optional;
import java.util.Set;

public interface VenueService {

    void addVenue(Venue venue, Long idTheater) throws Exception;

    //TODO UPDATE

    Optional<Venue> deleteVenue(Long id) throws VenueNotFoundException, VenueAlreadyExistException;

    void updateVenue(Long id, Venue venue) throws InvalidDataException, IllegalAccessException, VenueNotFoundException;

    Set<Optional<Venue>> getAllVenues();
}
