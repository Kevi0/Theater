package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Season;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    @NotNull Optional<Season> findSeasonById(@NotNull Long id);

    Optional<Season> findSeasonByTitle(String title);

    @Modifying
    @Query("UPDATE Season s SET s.deletedAt = CURRENT_TIMESTAMP WHERE s.id = :id")
    void deleteSeasonById(@Param("id") Long id);

    @Query("SELECT s FROM Season s WHERE s.deletedAt IS NULL")
    List<Optional<Season>> findAllSeasons();
}
