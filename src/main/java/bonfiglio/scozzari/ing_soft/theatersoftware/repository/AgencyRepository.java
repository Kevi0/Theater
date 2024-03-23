package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

    Optional<Agency> findByName(String name);

    Optional<Agency> findByEmail(String email);

    Optional<Agency> findByPec(String pec);

    Optional<Agency> findByTel1(String tel1);

}
