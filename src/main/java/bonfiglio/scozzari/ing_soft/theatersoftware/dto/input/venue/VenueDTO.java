package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.venue;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueDTO implements InputDTO {

    @NotBlank(message = "Il luogo non può essere vuoto")
    private String venue;

    @NotBlank(message = "Il nome non può essere vuoto")
    private String name;

    @NotBlank(message = "Il CAP non può essere vuoto")
    private String cap;

    @NotBlank(message = "La città non può essere vuota")
    private String city;

    @NotNull(message = "La capacità non può essere nulla")
    @Positive(message = "La capacità deve essere un numero positivo")
    private Long capacity;

    private Long idTheater;

}
