package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.BankAccountES;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountESRepository extends JpaRepository<BankAccountES, Long> {

    @NotNull Optional<BankAccountES> findBankAccountESById(@NotNull Long id);

}
