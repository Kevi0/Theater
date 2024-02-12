package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.BankAccountES;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.BankAccount;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.BankAccountESRepository;
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
