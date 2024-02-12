package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.BankAccountIT;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.BankAccount;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.BankAccountITRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.BankAccountITService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountITServiceImpl implements BankAccountITService {

    private final BankAccountITRepository bankAccountITRepository;

    @Override
    public BankAccount addBankAccountIT(BankAccountIT bankAccountIT) {
        bankAccountITRepository.save(bankAccountIT);
        return bankAccountIT;
    }

    @Override
    public Optional<BankAccountIT> deleteBankAccountIT(Long id) {
        return Optional.empty();
    }
}
