package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationAgency.AgencyDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.agencyMapper.AgencyMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.responses.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.AgencyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/agency")
public class AgencyController {

    private final AgencyServiceImpl agencyService;

    private final AgencyMapper agencyMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO agencyDTO
    ) throws Exception {

        if (agencyDTO instanceof AgencyDTO){
            agencyService.addAgency(agencyMapper.agencyDTOToAgency(agencyDTO));

            return new ResponseEntity<>(new ResponseMessage("Agency added"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Agency not added"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable Long id,
            @RequestBody InputDTO agencyDTO
    ) throws Exception {

        if (agencyDTO instanceof AgencyDTO){
            agencyService.updateAgency(id, agencyMapper.agencyDTOToAgency(agencyDTO));

            return new ResponseEntity<>("Agency updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Agency not updated", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            Optional<Agency> deletedAgency = agencyService.deleteAgency(id);
            return deletedAgency.map(agency ->
                            new ResponseEntity<>("Agency deleted", HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>("Agency not found", HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>("Agency not deleted", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAll")
    public List<Optional<Agency>> getAll(){
        return agencyService.getAllAgencies();
    }

    @GetMapping(value = "/getAgencyIdByName/{name}")
    public Long getAgencyIdByName(@PathVariable String name){
        return agencyService.getAgencyIdByName(name);
    }
}
