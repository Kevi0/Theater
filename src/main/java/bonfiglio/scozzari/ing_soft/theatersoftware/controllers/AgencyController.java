package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.agencyMapper.AgencyMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.AgencyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/agency")
public class AgencyController {

    private final AgencyServiceImpl agencyService;

    private final AgencyMapper agencyMapper;

    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody InputDTO agencyDTO
    ) throws Exception {
        agencyService.addAgency(agencyMapper.agencyDTOToAgency(agencyDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body("Agency successfully created!");
    }
}
