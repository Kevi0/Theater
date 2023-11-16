package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubUnemployedDTO implements SubWorkDTO {

    @NotBlank(message = "Please provide a valid allowance")
    private String allowance;

}