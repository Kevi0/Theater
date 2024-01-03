package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Opera;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OperaRepository extends JpaRepository<Opera, Long> {

    @NotNull Optional<Opera> findById(@NotNull Long id);

    Optional<Opera> findOperaByTitle(String title);

    @Modifying
    @Query("UPDATE Opera o SET o.deletedAt = CURRENT_TIMESTAMP WHERE o.id = :id")
    void deleteOperaById(@Param("id") Long id);

    @Query("SELECT o FROM Opera o WHERE o.deletedAt IS NULL")
    List<Optional<Opera>> findAllOperas();
}
