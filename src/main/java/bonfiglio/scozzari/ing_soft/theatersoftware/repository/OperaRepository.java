package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Opera;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface OperaRepository extends JpaRepository<Opera, Long> {

    @NotNull Optional<Opera> findById(@NotNull Long id);

    Optional<Opera> findOperaByTitle(String title);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM Opera o WHERE o.id = :id")
    boolean findOperaById(Long id);

}
