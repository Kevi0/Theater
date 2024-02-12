package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.interfaces.SubWorkDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubStudentDTO implements SubWorkDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String studies;

}
