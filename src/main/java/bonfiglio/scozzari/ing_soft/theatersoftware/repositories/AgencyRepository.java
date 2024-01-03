package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

    @NotNull Optional<Agency> findById(@NotNull Long id);

    Optional<Agency> findAgencyByName(String name);

    @Modifying
    @Query("UPDATE Agency a SET a.deletedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
    void deleteAgencyById(@Param("id") Long id);

    @Query("SELECT a FROM Agency a WHERE a.deletedAt IS NULL")
    List<Optional<Agency>> findAllAgencies();

}
