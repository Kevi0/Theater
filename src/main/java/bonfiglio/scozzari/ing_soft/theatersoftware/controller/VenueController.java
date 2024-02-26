package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.venue.VenueDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.venue.VenueMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Venue;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.VenueServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@SuppressWarnings("All")
@AllArgsConstructor
@RestController
@RequestMapping("/api/venue")
public class VenueController {

    private final VenueServiceImpl venueService;

    private final VenueMapper venueMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO venueDTO
    ) throws TheaterNotFoundException, VenueAlreadyExistException, InvalidDataException {
        try {

            if (venueDTO instanceof VenueDTO dto) {

                venueService.addVenue(venueMapper.venueDTOToVenue(venueDTO), dto.getIdTheaters());

                return new ResponseEntity<>(new ResponseMessage("Venue added"), HttpStatus.OK);

            } else {
                throw new IllegalArgumentException("Venue not added");
            }

        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Invalid data");
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO venueDTO
    ) throws VenueNotFoundException, InvalidDataException, IllegalAccessException {

        if (venueDTO instanceof VenueDTO){
            venueService.updateVenue(id, venueMapper.venueDTOToVenue(venueDTO));

            return new ResponseEntity<>(new ResponseMessage("Venue updated"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Venue not updated");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(
            @PathVariable Long id
    ) throws VenueNotFoundException, VenueAlreadyExistException {
        venueService.deleteVenue(id);

        return new ResponseEntity<>(new ResponseMessage("Venue deleted"), HttpStatus.OK);
    }

    @RequestMapping(value = "/venues", method = RequestMethod.GET)
    public ResponseEntity<Set<Optional<Venue>>> getVenues() {
        return new ResponseEntity<>(venueService.getAllVenues(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}/id", method = RequestMethod.GET)
    public ResponseEntity<Long> getVenueIdByName(
            @PathVariable String name
    ) throws VenueNotFoundException {
        return new ResponseEntity<>(venueService.getVenueIdByName(name), HttpStatus.OK);
    }

}