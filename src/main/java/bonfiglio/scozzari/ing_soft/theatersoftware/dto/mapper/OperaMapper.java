package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.OperaDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Opera;
import org.springframework.stereotype.Component;

@Component
public class OperaMapper {
    public Opera operaDTOToOpera(InputDTO operaDTO) {
        if (!(operaDTO instanceof OperaDTO operaData)){
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
