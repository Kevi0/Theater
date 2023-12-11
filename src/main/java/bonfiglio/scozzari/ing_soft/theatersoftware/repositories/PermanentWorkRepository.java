package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.PermanentWork;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermanentWorkRepository extends JpaRepository<PermanentWork, Long> {

    @NotNull Optional<PermanentWork> findPermanentWorkById(@NotNull Long id);
}
