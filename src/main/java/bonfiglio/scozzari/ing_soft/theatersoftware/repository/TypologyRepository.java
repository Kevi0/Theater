package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Typology;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypologyRepository extends JpaRepository<Typology, String> {

    @NotNull Optional<Typology> findTypologyById(@NotNull String id);

}
