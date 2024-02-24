package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Venue;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface VenueRepository extends JpaRepository<Venue, Long> {

    @NotNull Optional<Venue> findById(@NotNull Long id);
    Optional<Venue> findVenueByName(String name);

    @Modifying
    @Query("UPDATE Venue v SET v.deletedAt = CURRENT_TIMESTAMP WHERE v.id = :id")
    void deleteVenueById(@NotNull Long id);

    @Query("SELECT v FROM Venue v WHERE v.deletedAt IS NULL")
    Set<Optional<Venue>> findAllVenues();

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Venue v WHERE v.id = :id AND v.deletedAt IS NOT NULL")
    boolean checkIfVenueIsDeleted(Long id);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Venue v WHERE v.name = :name AND v.deletedAt IS NOT NULL")
    boolean findVenueByNameAndDeletedAtIsNull(String name);
}
