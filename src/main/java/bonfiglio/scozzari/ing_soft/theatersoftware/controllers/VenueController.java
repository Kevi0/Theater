package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationVenue.VenueDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.venueMapper.VenueMapper;
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
        if(venueDTO instanceof VenueDTO dto){
            venueService.addVenue(venueMapper.venueDTOToVenue(venueDTO), dto.getIdTheater());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Venue successfully created!");
    }
}