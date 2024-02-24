package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.venue;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.venue.VenueDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Venue;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {

    public Venue venueDTOToVenue(InputDTO venueDTO){
        if(!(venueDTO instanceof VenueDTO venueData)){
            return null;
        }

        Venue venue = new Venue();

        venue.setVenue(venueData.getVenue());
        venue.setName(venueData.getName());
        venue.setCap(venueData.getCap());
        venue.setCity(venueData.getCity());
        venue.setCapacity(venueData.getCapacity());

        return venue;

    }

}
