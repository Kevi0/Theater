package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.theater.TheaterMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.TheaterNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.responses.ResponseMessage;
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

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO theaterDTO
    ) throws TheaterAlreadyExistException, InvalidDataException {

        try {
            if (theaterDTO instanceof TheaterDTO){
                theaterService.addTheater(theaterMapper.theaterDTOToTheater(theaterDTO));

                return new ResponseEntity<>(new ResponseMessage("Theater added successfully!"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Theater not added!");
                //return new ResponseEntity<>(new ResponseMessage("Theater not added!"), HttpStatus.BAD_REQUEST);
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Invalid data");

        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO theaterDTO
    ) throws TheaterNotFoundException, InvalidDataException, IllegalAccessException {

        if (theaterDTO instanceof TheaterDTO){
            theaterService.updateTheater(id, theaterMapper.theaterDTOToTheater(theaterDTO));

            return new ResponseEntity<>(new ResponseMessage("Theater updated successfully!"), HttpStatus.OK);
        } else {
            //return new ResponseEntity<>(new ResponseMessage("Theater not updated!"), HttpStatus.BAD_REQUEST);
            throw new IllegalArgumentException("Theater not updated!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> delete(
            @PathVariable Long id
    ) throws TheaterNotFoundException, TheaterAlreadyDeletedException {

        if (theaterService.deleteTheater(id).isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Theater deleted successfully!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Theater not deleted!"), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/theaters")
    public Set<Optional<Theater>> getAll(){
        return theaterService.getAllTheaters();
    }

    @GetMapping(value = "/theaters/{name}")
    public Long getTheaterIdByName(@PathVariable String name){
        return theaterService.getTheaterIdByName(name);
    }

}
