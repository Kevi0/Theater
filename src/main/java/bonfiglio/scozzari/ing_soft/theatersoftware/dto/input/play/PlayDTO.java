package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.play;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayDTO implements InputDTO {

    private String name;

    private LocalDate datePlay;

}
