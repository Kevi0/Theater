package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

    Optional<Agency> findAgencyByName(String name);

}
