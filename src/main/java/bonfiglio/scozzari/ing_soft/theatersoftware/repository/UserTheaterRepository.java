package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserTheater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTheaterRepository extends JpaRepository<UserTheater, Long> {
}
