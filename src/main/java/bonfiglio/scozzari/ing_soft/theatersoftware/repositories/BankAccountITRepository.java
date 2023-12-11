package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.BankAccountIT;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountITRepository extends JpaRepository<BankAccountIT, Long> {
    @NotNull Optional<BankAccountIT> findBankAccountITById(@NotNull Long id);
}
