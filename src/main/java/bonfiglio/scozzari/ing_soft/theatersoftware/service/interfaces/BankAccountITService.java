package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.BankAccountIT;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.BankAccount;

import java.util.Optional;

public interface BankAccountITService {

    BankAccount addBankAccountIT(BankAccountIT bankAccountIT);

    //TODO UPDATE

    Optional<BankAccountIT> deleteBankAccountIT(Long id);

}
