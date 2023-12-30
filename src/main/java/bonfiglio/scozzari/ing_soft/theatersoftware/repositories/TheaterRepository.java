package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

    @NotNull Optional<Theater> findById(@NotNull Long id);

    Optional<Theater> findTheaterByName(String name);

    @Modifying
    @Query("UPDATE Theater t SET t.deletedAt = CURRENT_TIMESTAMP WHERE t.id = :id")
    void deleteTheaterById(@Param("id") Long id);

}
