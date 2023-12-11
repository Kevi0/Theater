package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.artistMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationArtist.subRegistrationArtistDTO.SubBankAccountITDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.BankAccountIT;
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
