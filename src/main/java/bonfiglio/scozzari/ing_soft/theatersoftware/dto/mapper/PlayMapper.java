package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.PlayDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Play;
import org.springframework.stereotype.Component;

@Component
public class PlayMapper {
    public Play playDTOToPlay(InputDTO playDTO) {
        if (!(playDTO instanceof PlayDTO playData)) {
            return null;
        }
        Play play = new Play();

        play.setName(playData.getName());
        play.setDatePlay(playData.getDatePlay());

        return play;
    }
}
