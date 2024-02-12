package bonfiglio.scozzari.ing_soft.theatersoftware.repository;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Artist;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @NotNull Optional<Artist> findById(@NotNull Long id);

    @Query("SELECT artist FROM Artist artist JOIN artist.user user WHERE user.username = :username")
    Optional<Artist> findArtistByUsername(String username);

    @Modifying
    @Query("UPDATE Artist artist SET artist.deletedAt = CURRENT_TIMESTAMP WHERE artist.id = :id")
    void deleteArtistById(Long id);

    @Query("SELECT artist FROM Artist artist WHERE artist.deletedAt IS NULL")
    List<Optional<Artist>> findAllArtists();

    @Query("SELECT CASE WHEN COUNT(artist) > 0 THEN true ELSE false END FROM Artist artist WHERE artist.id = :id AND artist.deletedAt IS NOT NULL")
    boolean checkIfArtistIsDeleted(Long id);

}
