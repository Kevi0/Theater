package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.agency.AgencyDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.agency.AgencyMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO agencyDTO
    ) throws AgencyAlreadyExistException, InvalidDataException {

        try {
            if (agencyDTO instanceof AgencyDTO) {
                agencyService.addAgency(agencyMapper.agencyDTOToAgency(agencyDTO));

                return new ResponseEntity<>(new ResponseMessage("Agency added"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Agency not added");
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Invalid data");
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO agencyDTO
    ) throws AgencyNotFoundException, InvalidDataException, IllegalAccessException {

        if (agencyDTO instanceof AgencyDTO){
            agencyService.updateAgency(id, agencyMapper.agencyDTOToAgency(agencyDTO));

            return new ResponseEntity<>(new ResponseMessage("Agency updated"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Agency not updated");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(
            @PathVariable Long id
    ) throws AgencyNotFoundException, AgencyAlreadyDeletedException {

        if (agencyService.deleteAgency(id).isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Agency deleted"), HttpStatus.OK);
        } else {
            throw new AgencyNotFoundException("Error when deleting the agency");
        }

    }


    @RequestMapping(value = "/agencies", method = RequestMethod.GET)
    public ResponseEntity<Set<Optional<Agency>>> getAll(){
        return new ResponseEntity<>(agencyService.getAllAgencies(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}/id", method = RequestMethod.GET)
    public ResponseEntity<Long> getAgencyIdByName(@PathVariable String name) throws AgencyNotFoundException {
        return new ResponseEntity<>(agencyService.getAgencyIdByName(name), HttpStatus.OK);
    }
}
