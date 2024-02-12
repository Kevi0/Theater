package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

    @NotNull Optional<Theater> findById(@NotNull Long id);

    Optional<Theater> findTheaterByName(String name);

    @Modifying
    @Query("UPDATE Theater t SET t.deletedAt = CURRENT_TIMESTAMP WHERE t.id = :id")
    void deleteTheaterById(@Param("id") Long id);

    @Query("SELECT t FROM Theater t WHERE t.deletedAt IS NULL")
    List<Optional<Theater>> findAllTheaters();

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Theater t WHERE t.id = :id AND t.deletedAt IS NOT NULL")
    //return true if there is a theater with id = :id and deletedAt set (the theater has been deleted)
    //return false if there is no theater with id = :id and deletedAt not set (the theater has not been deleted)
    boolean checkIfTheaterIsDeleted(Long id);
}
