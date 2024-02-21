package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

    @NotNull Optional<Agency> findById(@NotNull Long id);

    Optional<Agency> findAgencyByName(String name);

    @Modifying
    @Query("UPDATE Agency a SET a.deletedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
    void deleteAgencyById(@Param("id") Long id);

    @Query("SELECT a FROM Agency a WHERE a.deletedAt IS NULL")
    Set<Optional<Agency>> findAllAgencies();

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Agency a WHERE a.id = :id AND a.deletedAt IS NOT NULL")
    // return true se esiste un'agenzia con id = :id e deletedAt valorizzato (quindi l'agenzia è stata cancellata)
    // return false se non esiste un'agenzia con id = :id e deletedAt non valorizzato (quindi l'agenzia non è stata cancellata)
    boolean checkIfAgencyIsDeleted(Long id);

}
