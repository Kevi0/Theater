package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.BankAccountES;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.BankAccount;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.BankAccountESRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.BankAccountITRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.BankAccountESService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountESServiceImpl implements BankAccountESService {

    private final BankAccountESRepository bankAccountESRepository;

    @Override
    public BankAccount addBankAccountES(BankAccountES bankAccountES) {
        bankAccountESRepository.save(bankAccountES);
        return bankAccountES;
    }

    @Override
    public Optional<BankAccountES> deleteBankAccountES(Long id) {
        return Optional.empty();
    }
}
