package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationSeason.SeasonDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.seasonMapper.SeasonMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.SeasonServiceImpl;
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
public class SeasonController {

    private final SeasonServiceImpl seasonService;

    private final SeasonMapper seasonMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<String> create(
            @RequestBody InputDTO seasonDTO
    ) throws Exception {
        if (seasonDTO instanceof SeasonDTO){
            seasonService.addSeason(seasonMapper.seasonDTOToSeason(seasonDTO));

            return new ResponseEntity<>("Season added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Season not added", HttpStatus.BAD_REQUEST);

        }
    }

}
