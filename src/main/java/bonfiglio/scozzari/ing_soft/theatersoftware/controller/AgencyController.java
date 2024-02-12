package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.agency.AgencyDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.agency.AgencyMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.responses.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.AgencyServiceImpl;
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
@RequestMapping("/api/agency")
public class AgencyController {

    private final AgencyServiceImpl agencyService;

    private final AgencyMapper agencyMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO agencyDTO
    ) throws AgencyAlreadyExistException, InvalidDataException {

        try {
            if (agencyDTO instanceof AgencyDTO) {
                agencyService.addAgency(agencyMapper.agencyDTOToAgency(agencyDTO));

                return new ResponseEntity<>(new ResponseMessage("Agency added"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Agency not added");
                //return new ResponseEntity<>(new ResponseMessage("Agency not added"), HttpStatus.BAD_REQUEST);
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Invalid data");
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO agencyDTO
    ) throws AgencyNotFoundException, InvalidDataException, IllegalAccessException {

        if (agencyDTO instanceof AgencyDTO){
            agencyService.updateAgency(id, agencyMapper.agencyDTOToAgency(agencyDTO));

            return new ResponseEntity<>(new ResponseMessage("Agency updated"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Agency not updated");
            //return new ResponseEntity<>(new ResponseMessage("Agency not updated"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> delete(
            @PathVariable Long id
    ) throws AgencyNotFoundException, AgencyAlreadyDeletedException {

        if (agencyService.deleteAgency(id).isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Agency deleted"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Agency not deleted"), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/agencies")
    public Set<Optional<Agency>> getAll(){
        return agencyService.getAllAgencies();
    }

    @GetMapping(value = "/agencies/{name}")
    public Long getAgencyIdByName(@PathVariable String name){
        return agencyService.getAgencyIdByName(name);
    }
}
