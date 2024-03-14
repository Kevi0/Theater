package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserTheater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTheaterRepository extends JpaRepository<UserTheater, Long> {

    List<UserTheater> findByUser(User user);

    @Modifying
    @Query("DELETE FROM UserTheater ut WHERE ut.user = :user")
    void deleteByUser(User user);

}
