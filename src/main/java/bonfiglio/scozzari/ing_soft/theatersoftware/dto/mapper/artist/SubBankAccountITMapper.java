package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubBankAccountITDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.BankAccountIT;
import org.springframework.stereotype.Component;

@Component
public class SubBankAccountITMapper {

    public BankAccountIT bankAccountITDTOToBankAccountIT(
            SubBankAccountITDTO subBankAccountITDTO
    ){
        if(subBankAccountITDTO == null){
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
