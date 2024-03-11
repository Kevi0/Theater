package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Token;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Modifying
    @Query("DELETE FROM Token t WHERE t.token = :token")
    void deleteByToken(String token);

    @Query("SELECT COUNT(t) > 0 FROM Token t WHERE t.user = :user")
    /*
        Verifica se un utente ha già un token assegnato.
        Restituirà true se esiste un token associato all'utente specificato, altrimenti false.
    */
    boolean existsByUser(@Param("user") User user);

    @Query("SELECT t.token FROM Token t WHERE t.user = :user")
    /*
        Estrae il token associato a un utente specifico.
        Restituirà il token se esiste, altrimenti null.
    */
    String findTokenByUser(@Param("user") User user);

}
