package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Venue;

import java.util.Optional;

public interface VenueService {

    Optional<Venue> addVenue(Venue venue, Long idTheater) throws Exception;

    //TODO UPDATE

    Optional<Venue> deleteVenue(Long id);

}
