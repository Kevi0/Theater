package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.TypologyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Artist;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.BankAccount;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;

import java.util.Optional;
import java.util.Set;

public interface ArtistService {

    void addArtist(Artist artist, Long idUser, Set<String> idTypologies, Work work, BankAccount bankAccount) throws UserNotFoundException, TypologyNotFoundException;

    void updateArtist(Long id, Artist artist);

    Optional<Artist> deleteArtist(Long id);

    Set<Optional<Artist>> getAllArtists();

    Long getArtistIdByUsername(String username);

}
