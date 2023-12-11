package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.BankAccountES;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.BankAccount;

import java.util.Optional;

public interface BankAccountESService {

    BankAccount addBankAccountES(BankAccountES bankAccountES);

    //TODO UPDATE

    Optional<BankAccountES> deleteBankAccountES(Long id);

}
