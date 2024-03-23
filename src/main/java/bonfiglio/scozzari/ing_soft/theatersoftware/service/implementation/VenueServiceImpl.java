package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Venue;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.TheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.VenueRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.VenueService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.VenueRegistrationValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("All")
@Service
@Transactional
@AllArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final TheaterRepository theaterRepository;
    private final VenueRepository venueRepository;

    private final VenueRegistrationValidator validator;

    @Override
    public void addVenue(Venue venue, Set<Long> idTheaters) throws InvalidDataException, VenueAlreadyExistException, TheaterNotFoundException {

        if (!venueRepository.findVenueByName(venue.getName()).isPresent()) {

            validator.validate(venue);

            var venueToInsert = Venue.builder()
                    .name(venue.getName())
                    .venue(venue.getVenue())
                    .cap(venue.getCap())
                    .city(venue.getCity())
                    .capacity(venue.getCapacity())
                    .build();
            venueToInsert.setCreatedAt(LocalDateTime.now());

            for (Long idTheater : idTheaters) {
                Theater theater = theaterRepository.findById(idTheater)
                        .orElseThrow(() -> new TheaterNotFoundException("Theater not found with ID: " + idTheater));

                theater.getVenues().add(venueToInsert);

            }

            venueRepository.save(venueToInsert);

        } else {
            throw new VenueAlreadyExistException("Error when entering the venue");
        }
    }

    @Override
    public Optional<Venue> deleteVenue(Long id) throws VenueNotFoundException, VenueAlreadyExistException {
        Optional<Venue> venueToDelete = venueRepository.findById(id);

        if (venueToDelete.isPresent()) {

            Venue existingVenue = venueToDelete.get();
            venueRepository.delete(existingVenue);

        }
        else {
            throw new VenueNotFoundException("Error when deleting the venue");
        }

        return venueToDelete;
    }

    @Override
    public void updateVenue(Long id, Venue venue) throws InvalidDataException, IllegalAccessException, VenueNotFoundException {

        Optional<Venue> venueToUpdate = venueRepository.findById(id);

        if (venueToUpdate.isPresent()) {

            validator.validate(venue);
            Venue existingVenue = venueToUpdate.get();
            ObjectUpdater<Venue> venueUpdater = new ObjectUpdater<>();
            venueRepository.save(existingVenue);
        } else {
            throw new VenueNotFoundException("Error when updating the venue");
        }
    }

    @Override
    public List<Venue> getAllVenues() {
        try {
            return venueRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error when getting the venues");
        }
    }

    public Long getVenueIdByName(String name) throws VenueNotFoundException {
        return venueRepository.findVenueByName(name)
                .map(Venue::getId)
                .orElseThrow(() -> new VenueNotFoundException("Error when getting the venue"));
    }

}
