package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubRetiredDTO implements SubWorkDTO {

        @NotBlank(message = "Pension type cannot be empty")
        private Boolean isOver65;
}
