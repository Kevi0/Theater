package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationOpera.OperaDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.operaMapper.OperaMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Opera;
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
    public ResponseEntity<String> create(
            @RequestBody InputDTO operaDTO
    ) throws Exception {

        if (operaDTO instanceof OperaDTO){
            operaService.addOpera(operaMapper.operaDTOToOpera(operaDTO));

            return ResponseEntity.ok("Opera added");
        } else {
            return ResponseEntity.badRequest().body("Opera not added");

        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable Long id,
            @RequestBody InputDTO operaDTO
    ) throws Exception {

        if (operaDTO instanceof OperaDTO){
            operaService.updateOpera(id, operaMapper.operaDTOToOpera(operaDTO));

            return ResponseEntity.ok("Opera updated");
        } else {
            return ResponseEntity.badRequest().body("Opera not updated");

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id){
        try {
            Optional<Opera> deletedOpera = operaService.deleteOpera(id);
            return deletedOpera.map(opera ->
                    ResponseEntity.ok(new ResponseMessage("Opera deleted successfully!")))
                .orElseGet(() -> ResponseEntity.badRequest().body(new ResponseMessage("Opera not found!")));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Error deleting opera!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAll")
    public List<Optional<Opera>> getAll(){
        return operaService.getAllOperas();
    }

    @GetMapping(value = "/getOperaIdByTitle/{title}")
    public Long getOperaIdByTitle(@PathVariable String title){
        return operaService.getOperaIdByTitle(title);
    }

}
