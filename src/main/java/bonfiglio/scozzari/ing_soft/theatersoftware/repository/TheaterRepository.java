package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater, Long> {

    Optional<Theater> findByName(String name);

    Optional<Theater> findByTel(String tel);

    Optional<Theater> findByEmail(String email);

    Optional<Theater> findByPec(String pec);

    Optional<Theater> findByIva(String iva);

    Optional<Theater> findByUniqueCode(String uniqueCode);

    Optional<Theater> findByRecipientCode(String recipientCode);

}
