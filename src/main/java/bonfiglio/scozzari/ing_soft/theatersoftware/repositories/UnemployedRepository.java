package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Unemployed;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnemployedRepository extends JpaRepository<Unemployed, Long> {

    @NotNull Optional<Unemployed> findUnemployedById(@NotNull Long id);

}
