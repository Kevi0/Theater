package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Venue;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.TheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.VenueRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.VenueService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final TheaterRepository theaterRepository;

    private final VenueRepository venueRepository;

    @Override
    public Optional<Venue> addVenue(Venue venue, Long idTheater) throws Exception {
        Optional<Theater> theater = theaterRepository.findById(idTheater);
        if(theater.isEmpty())
            throw new Exception("Theater not found!"); //TODO Custom TheaterNotFoundException
        else {
            if(venueRepository.findById(venue.getId()).isPresent())
                throw new Exception("Venue already exist!"); //TODO Custom VenueNotFoundException
            venue.getTheaters().add(theater.get());
            return Optional.of(venueRepository.save(venue));
        }
    }

    public void addVenue(Venue venue) throws Exception {
        if(venueRepository.findById(venue.getId()).isPresent())
            throw new Exception("Venue already exist!"); //TODO Custom VenueNotFoundException
        venueRepository.save(venue);
    }

    @Override
    public Optional<Venue> deleteVenue(Long id) {
        return Optional.empty();
    }
}
