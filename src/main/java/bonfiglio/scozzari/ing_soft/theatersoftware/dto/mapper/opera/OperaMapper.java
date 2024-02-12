package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.opera;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.opera.OperaDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Opera;
import org.springframework.stereotype.Component;

@Component
public class OperaMapper {

    public Opera operaDTOToOpera(InputDTO operaDTO){
        if(!(operaDTO instanceof OperaDTO operaData)){
            return null;
        }
        else {
            Opera opera = new Opera();

            opera.setTitle(operaData.getTitle());
            opera.setStartDate(operaData.getStartDate());
            opera.setStartRehearsal(operaData.getStartRehearsal());

            return opera;
        }
    }

}
