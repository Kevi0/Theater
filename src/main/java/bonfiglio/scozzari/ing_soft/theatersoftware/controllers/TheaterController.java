package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationTheater.TheaterDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.theaterMapper.TheaterMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.TheaterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    private TheaterServiceImpl theaterService;

    private final TheaterMapper theaterMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<String> create(
            @RequestBody InputDTO theaterDTO
    ) throws Exception {
        if (theaterDTO instanceof TheaterDTO){
            theaterService.addTheater(theaterMapper.theaterDTOToTheater(theaterDTO));

            return new ResponseEntity<>("Theater added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Theater not added", HttpStatus.BAD_REQUEST);

        }
    }

}
