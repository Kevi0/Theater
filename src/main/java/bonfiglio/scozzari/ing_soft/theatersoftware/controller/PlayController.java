package bonfiglio.scozzari.ing_soft.theatersoftware.controller;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.play.PlayDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.play.PlayMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.play.PlayAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.play.PlayAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.play.PlayNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Play;
import bonfiglio.scozzari.ing_soft.theatersoftware.response.ResponseMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.PlayServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@SuppressWarnings("deprecation")
@AllArgsConstructor
@RestController
@RequestMapping("/api/play")
public class PlayController {

    private final PlayServiceImpl playService;

    private final PlayMapper playMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> create(
            @RequestBody InputDTO playDTO
    ) throws PlayAlreadyExistException, InvalidDataException {

        try {
            if (playDTO instanceof PlayDTO) {
                playService.addPlay(playMapper.playDTOToPlay(playDTO));

                return new ResponseEntity<>(new ResponseMessage("Play added"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Play not added");
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Invalid data");
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> update(
            @PathVariable Long id,
            @RequestBody InputDTO playDTO
    ) throws InvalidDataException, IllegalAccessException, PlayNotFoundException {

        if (playDTO instanceof PlayDTO){
            playService.updatePlay(id, playMapper.playDTOToPlay(playDTO));

            return new ResponseEntity<>(new ResponseMessage("Play updated"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Play not updated");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> delete(
            @PathVariable Long id
    ) throws PlayNotFoundException, PlayAlreadyDeletedException {

        if (playService.deletePlay(id).isPresent()){
            return new ResponseEntity<>(new ResponseMessage("Play deleted"), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Play not deleted");
        }
    }

    @RequestMapping(value = "/plays", method = RequestMethod.GET)
    public ResponseEntity<Set<Optional<Play>>> getAll() {
        return new ResponseEntity<>(playService.getAllPlays(), HttpStatus.OK);
    }

    @RequestMapping(value = "/playId/{name}", method = RequestMethod.GET)
    public ResponseEntity<Long> getPlayIdByName(@PathVariable String name) throws PlayNotFoundException {
        return new ResponseEntity<>(playService.getPlayIdByName(name), HttpStatus.OK);
    }

}
