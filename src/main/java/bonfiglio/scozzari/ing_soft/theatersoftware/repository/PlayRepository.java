package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayRepository extends JpaRepository<Play, Long> {

    Optional<Play> findPlayByName(String name);

}
