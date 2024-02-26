package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubBankAccountESDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.artist.sub.SubBankAccountITDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "BankAccountType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SubBankAccountITDTO.class, name = "IT"),
        @JsonSubTypes.Type(value = SubBankAccountESDTO.class, name = "ES")
})
public interface SubBankAccountDTO {
}
