package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.interfaces.SubBankAccountDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubBankAccountITDTO implements SubBankAccountDTO {

    private String beneficiaryName;

    private String bankName;

    private String abi;

    private String cab;

    private String iban;

}
