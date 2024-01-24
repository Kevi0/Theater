package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.middleTables.UserAgency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAgencyRepository extends JpaRepository<UserAgency, Long> {
}
