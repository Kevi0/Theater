package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.play;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.play.PlayDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Play;
import org.springframework.stereotype.Component;

@Component
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
