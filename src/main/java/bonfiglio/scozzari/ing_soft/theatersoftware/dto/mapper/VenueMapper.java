package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.VenueDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Venue;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {
    public Venue venueDTOToVenue (InputDTO venueDTO){

        if (!(venueDTO instanceof VenueDTO venueData))
            return null;
        else {
            Venue venue = new Venue();

            venue.setVenue(venueData.getVenue());
            venue.setCap(venueData.getCap());
            venue.setCity(venueData.getCity());
            venue.setCapacity(venueData.getCapacity());

            return venue;
        }
    }
}
