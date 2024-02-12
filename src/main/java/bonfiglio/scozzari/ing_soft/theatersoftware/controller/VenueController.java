package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.venue.VenueDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.venue.VenueMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.VenueServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/venue")
public class VenueController {

    private final VenueServiceImpl venueService;

    private final VenueMapper venueMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<String> create(
            @RequestBody InputDTO venueDTO
    ) throws Exception {
        if (venueDTO instanceof VenueDTO){
            venueService.addVenue(venueMapper.venueDTOToVenue(venueDTO));

            return new ResponseEntity<>("Venue added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Venue not added", HttpStatus.BAD_REQUEST);

        }
    }

}