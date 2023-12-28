package bonfiglio.scozzari.ing_soft.theatersoftware.controllers;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationPlay.PlayDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.playMapper.PlayMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.PlayServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/play")
public class PlayController {

    private final PlayServiceImpl playService;

    private final PlayMapper playMapper;

    @PostMapping(value = "/add")
    public ResponseEntity<String> create(
            @RequestBody InputDTO playDTO
    ) throws Exception {
        if (playDTO instanceof PlayDTO){
            playService.addPlay(playMapper.playDTOToPlay(playDTO));

            return ResponseEntity.ok("Play added");
        } else {
            return ResponseEntity.badRequest().body("Play not added");

        }
    }

}
