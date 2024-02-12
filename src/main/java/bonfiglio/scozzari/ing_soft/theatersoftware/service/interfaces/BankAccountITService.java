package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.BankAccountIT;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.BankAccount;

import java.util.Optional;

public interface BankAccountITService {

    BankAccount addBankAccountIT(BankAccountIT bankAccountIT);

    //TODO UPDATE

    Optional<BankAccountIT> deleteBankAccountIT(Long id);

}
