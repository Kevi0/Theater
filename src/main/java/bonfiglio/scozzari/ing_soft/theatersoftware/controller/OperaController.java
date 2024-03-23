package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.opera.OperaDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.opera.OperaMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.venue.VenueNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Opera;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.OperaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("deprecation")
@AllArgsConstructor
@RestController
@RequestMapping("/api/opera")
public class OperaController {

    private final OperaServiceImpl operaService;

    private final OperaMapper operaMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO operaDTO
    ) throws OperaAlreadyExistException, InvalidDataException, SeasonNotFoundException, VenueNotFoundException {

        try {
            if (operaDTO instanceof OperaDTO dto){
                operaService.addOpera(operaMapper.operaDTOToOpera(operaDTO), dto.getIdVenue(), dto.getIdSeasons());

                return new ResponseEntity<>(new ResponseMessage("Opera added"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Opera not added");
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Invalid data");
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO operaDTO
    ) throws OperaNotFoundException, InvalidDataException, IllegalAccessException {

        if (operaDTO instanceof OperaDTO){
            operaService.updateOpera(id, operaMapper.operaDTOToOpera(operaDTO));

            return new ResponseEntity<>(new ResponseMessage("Opera updated"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Opera not updated");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(
            @PathVariable Long id
    ) throws OperaNotFoundException {

        if (operaService.deleteOpera(id).isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Opera deleted"), HttpStatus.OK);
        } else {
            throw new OperaNotFoundException("Error when deleting the opera");
        }
    }

    @RequestMapping(value = "/operas", method = RequestMethod.GET)
    public List<Opera> getAll(){
        return operaService.getAllOperas();
    }

    @RequestMapping(value = "/operas/{title}", method = RequestMethod.GET)
    public Long getOperaIdByTitle(@PathVariable String title) throws OperaNotFoundException {
        return operaService.getOperaIdByTitle(title);
    }

}
