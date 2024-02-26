package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.opera;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperaDTO implements InputDTO {

    private String title;

    private LocalDate startDate;

    private LocalDate startRehearsal;

    private Long idVenue;

    private Set<Long> idSeasons;

}
