package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Retired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RetiredRepository extends JpaRepository<Retired, Long> {

    Optional<Retired> findRetiredById(Retired retired);

}
