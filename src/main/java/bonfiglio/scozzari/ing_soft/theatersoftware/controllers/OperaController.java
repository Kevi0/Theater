package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationOpera.OperaDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.operaMapper.OperaMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.OperaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
