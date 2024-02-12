package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.opera.OperaDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.opera.OperaMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.opera.OperaNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Opera;
import bonfiglio.scozzari.ing_soft.theatersoftware.responses.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.OperaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/opera")
public class OperaController {

    private final OperaServiceImpl operaService;

    private final OperaMapper operaMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO operaDTO
    ) throws OperaAlreadyExistException, InvalidDataException {

        if (operaDTO instanceof OperaDTO){
            operaService.addOpera(operaMapper.operaDTOToOpera(operaDTO));

            return new ResponseEntity<>(new ResponseMessage("Opera added"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Opera not added"), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO operaDTO
    ) throws OperaNotFoundException, InvalidDataException, IllegalAccessException {

        if (operaDTO instanceof OperaDTO){
            operaService.updateOpera(id, operaMapper.operaDTOToOpera(operaDTO));

            return new ResponseEntity<>(new ResponseMessage("Opera updated"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Opera not updated"), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id){
        try {
            Optional<Opera> deletedOpera = operaService.deleteOpera(id);
            return deletedOpera.map(opera ->
                    new ResponseEntity<>(new ResponseMessage("Opera deleted successfully!"), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ResponseMessage("Opera not deleted!"), HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Error deleting opera!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/operas")
    public List<Optional<Opera>> getAll(){
        return operaService.getAllOperas();
    }

    @GetMapping(value = "/operas/{title}")
    public Long getOperaIdByTitle(@PathVariable String title){
        return operaService.getOperaIdByTitle(title);
    }

}
