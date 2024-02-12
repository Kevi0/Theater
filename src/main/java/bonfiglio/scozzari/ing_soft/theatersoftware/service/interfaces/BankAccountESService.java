package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.BankAccountES;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.BankAccount;

import java.util.Optional;

public interface BankAccountESService {

    BankAccount addBankAccountES(BankAccountES bankAccountES);

    //TODO UPDATE

    Optional<BankAccountES> deleteBankAccountES(Long id);

}
