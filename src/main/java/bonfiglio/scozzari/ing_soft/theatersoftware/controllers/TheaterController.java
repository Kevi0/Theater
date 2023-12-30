package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationTheater.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.theaterMapper.TheaterMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.responses.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.TheaterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    private TheaterServiceImpl theaterService;

    private final TheaterMapper theaterMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO theaterDTO
    ) throws Exception {
        if (theaterDTO instanceof TheaterDTO){
            theaterService.addTheater(theaterMapper.theaterDTOToTheater(theaterDTO));

            return new ResponseEntity<>(new ResponseMessage("Theater added successfully!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Theater not added!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO theaterDTO
    ) throws Exception {
        if (theaterDTO instanceof TheaterDTO){
            theaterService.updateTheater(id, theaterMapper.theaterDTOToTheater(theaterDTO));

            return new ResponseEntity<>(new ResponseMessage("Theater updated successfully!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Theater not updated!"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) {
        try {
            Optional<Theater> deletedTheater = theaterService.deleteTheater(id);
            return deletedTheater.map(theater ->
                            new ResponseEntity<>(new ResponseMessage("Theater deleted successfully!"), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(new ResponseMessage("Theater not deleted or already deleted!"), HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Error deleting theater: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAll")
        public List<Optional<Theater>> getAll(){
        return theaterService.getAllTheaters();
    }

}
