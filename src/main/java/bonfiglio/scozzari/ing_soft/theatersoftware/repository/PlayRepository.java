package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Play;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PlayRepository extends JpaRepository<Play, Long> {

    @NotNull Optional<Play> findById(@NotNull Long id);

    Optional<Play> findPlayByName(String name);

    @Modifying
    @Query("UPDATE Play p SET p.deletedAt = CURRENT_TIMESTAMP WHERE p.id = :id")
    void deletePlayById(@Param("id") Long id);

    @Query("SELECT p FROM Play p WHERE p.deletedAt IS NULL")
    Set<Optional<Play>> findAllPlays();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Play p WHERE p.id = :id AND p.deletedAt IS NOT NULL")
    boolean checkIfPlayIsDeleted(Long id);

}
