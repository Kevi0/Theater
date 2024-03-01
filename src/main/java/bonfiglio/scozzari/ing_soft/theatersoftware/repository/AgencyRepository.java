package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

    Optional<Agency> findByName(String name);

    Optional<Agency> findByEmail(String email);

    @Modifying
    @Query("UPDATE Agency a SET a.deletedAt = CURRENT_TIMESTAMP WHERE a.id = :id")
    void softDeleteById(@Param("id") Long id);

    @Query("SELECT a FROM Agency a WHERE a.deletedAt IS NULL")
    Set<Optional<Agency>> findAllByDeletedAtIsNull();

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Agency a WHERE a.id = :id AND a.deletedAt IS NOT NULL")
    boolean existsByIdAndDeletedAtIsNotNull(Long id);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Agency a WHERE a.email = :email AND a.deletedAt IS NOT NULL")
    boolean existsByEmailAndDeletedAtIsNotNull(String email);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Agency a WHERE a.email = :email")
    boolean existsByEmail(String email);

}
