package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.artist;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubBankAccountESDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.BankAccountES;
import org.springframework.stereotype.Component;

@Component
public class SubBankAccountESMapper {

    public BankAccountES bankAccountESDTOToBankAccountES(
            SubBankAccountESDTO subBankAccountESDTO
    ){
        if(subBankAccountESDTO == null){
            return null;
        }

        BankAccountES bankAccountES = new BankAccountES();

        bankAccountES.setBeneficiaryName(subBankAccountESDTO.getBeneficiaryName());
        bankAccountES.setIban(subBankAccountESDTO.getIban());
        bankAccountES.setBicSwift(subBankAccountESDTO.getBicSwift());

        return bankAccountES;
    }

}
