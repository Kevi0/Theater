package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Venue;

import java.util.Optional;
import java.util.Set;

public interface VenueService {

    void addVenue(Venue venue, Set<Long> idTheaters) throws Exception;

    void updateVenue(Long id, Venue venue) throws InvalidDataException, IllegalAccessException, VenueNotFoundException;

    Optional<Venue> deleteVenue(Long id) throws VenueNotFoundException, VenueAlreadyExistException;

    Set<Optional<Venue>> getAllVenues();
}
