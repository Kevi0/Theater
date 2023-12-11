package bonfiglio.scozzari.ing_soft.theatersoftware.repositories;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("SELECT artist FROM Artist artist JOIN artist.user user WHERE user.username = :username")
    Optional<Artist> findArtistByUsername(String username);

}
