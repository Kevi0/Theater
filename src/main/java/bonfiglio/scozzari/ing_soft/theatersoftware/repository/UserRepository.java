package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @NotNull Optional<User> findById(@NotNull Long id);

    Optional<User> findUserByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.id = :id")
    void deleteUserById(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE u.deletedAt IS NULL")
    List<Optional<User>> findAllUsers();

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.id = :id AND u.deletedAt IS NOT NULL")
    boolean checkIfUserIsDeleted(Long id);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username AND u.deletedAt IS NOT NULL")

    /*
        Se la query ritorna true, significa che esiste almeno un utente con l'username specificato che è stato soft-deleted.
        Se ritorna false, significa che non esiste alcun utente con l'username specificato o che tutti gli utenti con quell'username non sono stati cancellati.
    */

    boolean findByUsernameAndDeletedAtIsNull(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")

    /*
        Il risultato di questa query sarà true se esiste almeno un utente con l'username specificato nel database, altrimenti sarà false.
    */

    boolean findByUsername(String username);

}
