package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Artist;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.BankAccount;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;

import java.util.Optional;
import java.util.Set;

public interface ArtistService {

    Optional<Artist> addArtist(Artist artist, Long idUser, Set<String> idTypologies, Work work, BankAccount bankAccount) throws Exception;

    //TODO UPDATE

    Optional<Artist> deleteArtist(Long id);

}
