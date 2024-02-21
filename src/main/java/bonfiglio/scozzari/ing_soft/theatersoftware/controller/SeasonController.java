package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.season.SeasonDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.season.SeasonMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season.SeasonNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Season;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.SeasonServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@SuppressWarnings("All")
@AllArgsConstructor
@RestController
@RequestMapping("/api/season")
public class SeasonController {

    private final SeasonServiceImpl seasonService;

    private final SeasonMapper seasonMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO seasonDTO
    ) throws SeasonAlreadyExistException, InvalidDataException {

        try {
            if (seasonDTO instanceof SeasonDTO) {
                seasonService.addSeason(seasonMapper.seasonDTOToSeason(seasonDTO));

                return new ResponseEntity<>(new ResponseMessage("Season added"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Season not added");
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Invalid data");
        }

    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO seasonDTO
    ) throws SeasonNotFoundException, InvalidDataException, IllegalAccessException {

        if (seasonDTO instanceof SeasonDTO){
            seasonService.updateSeason(id, seasonMapper.seasonDTOToSeason(seasonDTO));

            return new ResponseEntity<>(new ResponseMessage("Season updated"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Season not updated");
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(
            @PathVariable Long id
    ) throws SeasonNotFoundException, SeasonAlreadyDeletedException {

        if (seasonService.deleteSeason(id).isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Season deleted"), HttpStatus.OK);
        } else {
            throw new SeasonNotFoundException("Error when deleting the season");
        }

    }

    @RequestMapping(value = "/seasons", method = RequestMethod.GET)
    public ResponseEntity<Set<Optional<Season>>> getAll(){
        return new ResponseEntity<>(seasonService.getAllSeasons(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{title}/id", method = RequestMethod.GET)
    public ResponseEntity<Long> getSeasonIdByTitle(@PathVariable String title) throws SeasonNotFoundException {
        return new ResponseEntity<>(seasonService.getSeasonIdByTitle(title), HttpStatus.OK);
    }

}
