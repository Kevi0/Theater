package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.agency.AgencyDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.agency.AgencyMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.DataAccessServiceException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.DuplicateEmailException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.DuplicatePecException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater.DuplicateTelException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.AgencyServiceImpl;
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
@RequestMapping("/api/agency")
public class AgencyController {

    private final AgencyServiceImpl agencyService;

    private final AgencyMapper agencyMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO agencyDTO
    ) throws AgencyAlreadyExistException, InvalidDataException, UserNotFoundException, DuplicatePecException, DuplicateEmailException, DuplicateTelException {

        try {
            if (agencyDTO instanceof AgencyDTO dto) {
                agencyService.addAgency(agencyMapper.agencyDTOToAgency(agencyDTO), dto.getIdUsers());

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
    ) throws AgencyNotFoundException {

        if (agencyService.deleteAgency(id).isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Agency deleted"), HttpStatus.OK);
        } else {
            throw new AgencyNotFoundException("Error when deleting the agency");
        }

    }


    @RequestMapping(value = "/agencies", method = RequestMethod.GET)
    public ResponseEntity<List<Agency>> getAll() throws DataAccessServiceException {
        return new ResponseEntity<>(agencyService.getAllAgencies(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}/id", method = RequestMethod.GET)
    public ResponseEntity<Long> getAgencyIdByName(@PathVariable String name) throws AgencyNotFoundException {
        return new ResponseEntity<>(agencyService.getAgencyIdByName(name), HttpStatus.OK);
    }
}
