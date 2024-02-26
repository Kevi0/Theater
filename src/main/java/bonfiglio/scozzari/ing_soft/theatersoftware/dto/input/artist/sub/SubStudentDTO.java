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

    private String studies;

}
