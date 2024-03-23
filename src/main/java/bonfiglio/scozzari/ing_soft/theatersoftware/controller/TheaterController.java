package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater.TheaterSummaryDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.theater.TheaterUpdateRequestDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.theater.TheaterMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.theater.TheaterUpdateRequestMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.DataAccessServiceException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UnregisteredUserException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.TheaterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("deprecation")
@AllArgsConstructor
@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    private TheaterServiceImpl theaterService;

    private final TheaterMapper theaterMapper;
    private final TheaterUpdateRequestMapper theaterUpdateRequestMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO theaterDTO

    ) throws TheaterAlreadyExistException, InvalidDataException, UserNotFoundException,
            UnregisteredTheaterException, UnregisteredUserException, DuplicateNameException,
            DuplicatePecException, DuplicateEmailException, DuplicateTelException,
            DuplicateRecipientCodeException, DuplicateUniqueCodeException {

        try {
            if (theaterDTO instanceof TheaterDTO dto){
                theaterService.addTheater(theaterMapper.theaterDTOToTheater(theaterDTO), dto.getIdUsers());

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
            @RequestBody InputDTO requestDTO
    ) throws TheaterNotFoundException, InvalidDataException, IllegalAccessException {

        if (requestDTO instanceof TheaterUpdateRequestDTO) {
            theaterService.updateTheater(id, theaterUpdateRequestMapper.theaterDTOToTheater(requestDTO));

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
    public ResponseEntity<List<TheaterSummaryDTO>> getAll() throws DataAccessServiceException {
        return new ResponseEntity<>(theaterService.getAllTheaters(), HttpStatus.OK);
    }

    @RequestMapping(value = "/theaters/{name}", method = RequestMethod.GET)
    public ResponseEntity<Long> getTheaterIdByName(@PathVariable String name) throws TheaterNotFoundException {
        return new ResponseEntity<>(theaterService.getTheaterIdByName(name), HttpStatus.OK);
    }

}
