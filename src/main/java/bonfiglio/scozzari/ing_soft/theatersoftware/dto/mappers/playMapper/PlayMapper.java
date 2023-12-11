package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.playMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationPlay.PlayDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Play;

public class PlayMapper {

    public Play playDTOToPlay(InputDTO playDTO){
        if(!(playDTO instanceof PlayDTO playData)){
            return null;
        }

        Play play = new Play();

        play.setName(playData.getName());
        play.setDatePlay(playData.getDatePlay());

        return play;

    }

}
