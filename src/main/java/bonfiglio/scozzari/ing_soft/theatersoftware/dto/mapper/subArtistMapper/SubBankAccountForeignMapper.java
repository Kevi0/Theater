package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.subArtistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO.SubBankAccountForeignDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.BankAccountForeign;
import org.springframework.stereotype.Component;

@Component
public class SubBankAccountForeignMapper {
    public static BankAccountForeign subBankAccountForeignDTOToBankAccountForeign (SubBankAccountForeignDTO subBankAccountForeignDTO) {
        if (subBankAccountForeignDTO == null) {
            return null;
        }

        BankAccountForeign bankAccountForeign = new BankAccountForeign();

        bankAccountForeign.setBeneficiaryName(subBankAccountForeignDTO.getBeneficiaryName());
        bankAccountForeign.setIban(subBankAccountForeignDTO.getIban());
        bankAccountForeign.setBicSwift(subBankAccountForeignDTO.getBicSwift());

        return bankAccountForeign;
    }
}