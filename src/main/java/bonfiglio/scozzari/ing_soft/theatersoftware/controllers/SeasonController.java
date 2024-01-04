package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationSeason.SeasonDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.seasonMapper.SeasonMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Season;
import bonfiglio.scozzari.ing_soft.theatersoftware.responses.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.SeasonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/season")
public class SeasonController {

    private final SeasonServiceImpl seasonService;

    private final SeasonMapper seasonMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO seasonDTO
    ) throws Exception {
        if (seasonDTO instanceof SeasonDTO){
            seasonService.addSeason(seasonMapper.seasonDTOToSeason(seasonDTO));

            return new ResponseEntity<>(new ResponseMessage("Season added"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Season not added"), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO seasonDTO
    ) throws Exception {
        if (seasonDTO instanceof SeasonDTO){
            seasonService.updateSeason(id, seasonMapper.seasonDTOToSeason(seasonDTO));

            return new ResponseEntity<>(new ResponseMessage("Season updated"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Season not updated"), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id){
        try {
            Optional<Season> deletedSeason = seasonService.deleteSeason(id);
            return deletedSeason.map(season ->
                    new ResponseEntity<>(new ResponseMessage("Season deleted successfully!"), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ResponseMessage("Season not deleted!"), HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Season not deleted!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public List<Optional<Season>> getAll(){
        return seasonService.getAllSeasons();
    }

    @GetMapping("/getSeasonIdByTitle/{title}")
    public Long getSeasonIdByTitle(@PathVariable String title){
        return seasonService.getSeasonIdByTitle(title);
    }

}
