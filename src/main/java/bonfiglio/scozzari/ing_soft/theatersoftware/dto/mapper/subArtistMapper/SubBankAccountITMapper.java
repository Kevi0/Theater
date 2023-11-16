package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.subArtistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.subArtistDTO.SubBankAccountITDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.BankAccountIT;
import org.springframework.stereotype.Component;

@Component
public class SubBankAccountITMapper {
    public static BankAccountIT SubBankAccountITDTOToBankAccountIT (SubBankAccountITDTO subBankAccountITDTO) {
        if (subBankAccountITDTO == null) {
            return null;
        }

        BankAccountIT bankAccountIT = new BankAccountIT();

        bankAccountIT.setBeneficiaryName(subBankAccountITDTO.getBeneficiaryName());
        bankAccountIT.setBankName(subBankAccountITDTO.getBankName());
        bankAccountIT.setAbi(subBankAccountITDTO.getAbi());
        bankAccountIT.setCab(subBankAccountITDTO.getCab());
        bankAccountIT.setIban(subBankAccountITDTO.getIban());

        return bankAccountIT;
    }
}
