package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.TemporaryWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryWorkRepository extends JpaRepository<TemporaryWork, Long> {

    Optional<TemporaryWork> findTemporaryWorkById(TemporaryWork temporaryWork);

}
