package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubBankAccountForeignDTO {

    @NotBlank(message = "Beneficiary name cannot be empty")
    @Size(max = 255, message = "Beneficiary name must be at most 50 characters long")
    private String beneficiaryName;

    @NotBlank(message = "IBAN cannot be empty")
    @Size(min = 27, max = 27, message = "Must be a valid IBAN (27 characters)")
    private String iban;

    @NotBlank(message = "BIC/SWIFT cannot be empty")
    @Pattern(regexp = "[A-Z0-9]{8,11}", message = "Must be a valid BIC/SWIFT (8 to 11 characters)")
    @Size(min = 8, max = 11, message = "Must be a valid BIC/SWIFT (8 to 11 characters)")
    private String bicSwift;

}
