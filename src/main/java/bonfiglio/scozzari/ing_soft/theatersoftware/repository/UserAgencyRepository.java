package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAgencyRepository extends JpaRepository<UserAgency, Long> {

    List<UserAgency> findByUser(User user);

    @Modifying
    @Query("DELETE FROM UserAgency ua WHERE ua.user = :user")
    void deleteByUser(User user);

}
