package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationPlay;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
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

    @NotBlank(message = "Il nome non può essere vuoto")
    private String name;

    @NotNull(message = "La data di gioco non può essere nulla")
    @PastOrPresent(message = "La data di gioco deve essere nel passato o nel presente")
    private LocalDate datePlay;

}
