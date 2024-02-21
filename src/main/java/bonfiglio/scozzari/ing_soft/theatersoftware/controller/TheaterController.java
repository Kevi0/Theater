package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.theater.TheaterMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.TheaterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@SuppressWarnings("deprecation")
@AllArgsConstructor
@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    private TheaterServiceImpl theaterService;

    private final TheaterMapper theaterMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO theaterDTO
    ) throws TheaterAlreadyExistException, InvalidDataException {

        try {
            if (theaterDTO instanceof TheaterDTO){
                theaterService.addTheater(theaterMapper.theaterDTOToTheater(theaterDTO));

                return new ResponseEntity<>(new ResponseMessage("Theater added successfully!"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Theater not added!");
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Invalid data");

        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO theaterDTO
    ) throws TheaterNotFoundException, InvalidDataException, IllegalAccessException {

        if (theaterDTO instanceof TheaterDTO){
            theaterService.updateTheater(id, theaterMapper.theaterDTOToTheater(theaterDTO));

            return new ResponseEntity<>(new ResponseMessage("Theater updated successfully!"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Theater not updated!");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(
            @PathVariable Long id
    ) throws TheaterNotFoundException, TheaterAlreadyDeletedException {

        if (theaterService.deleteTheater(id).isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Theater deleted successfully!"), HttpStatus.OK);
        } else {
            throw new TheaterNotFoundException("Theater not found!");
        }

    }

    @RequestMapping(value = "/theaters", method = RequestMethod.GET)
    public ResponseEntity<Set<Optional<Theater>>> getAll(){
        return new ResponseEntity<>(theaterService.getAllTheaters(), HttpStatus.OK);
    }

    @RequestMapping(value = "/theaters/{name}", method = RequestMethod.GET)
    public ResponseEntity<Long> getTheaterIdByName(@PathVariable String name) throws TheaterNotFoundException {
        return new ResponseEntity<>(theaterService.getTheaterIdByName(name), HttpStatus.OK);
    }

}
