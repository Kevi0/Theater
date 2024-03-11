package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.TypologyNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.BankAccount;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.ArtistRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.TypologyRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.ArtistService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final UserRepository userRepository;
    private final TypologyRepository typologyRepository;
    private final ArtistRepository artistRepository;

    @Override
    public void addArtist(
            Artist artist,
            Long idUser,
            Set<String> idTypologies,
            Work work,
            BankAccount bankAccount
    ) throws TypologyNotFoundException, UserNotFoundException {

        // Associa l'artista all'utente
        User user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
        artist.setUser(user);

        // Associa le tipologie all'artista
        for(String idTypology : idTypologies){

            Typology typology = typologyRepository.findById(idTypology)
                    .orElseThrow(() -> new TypologyNotFoundException("Typology not found with ID: " + idTypology));

            Set<Typology> typologies = artist.getTypologies() != null ? artist.getTypologies() : new HashSet<>();
            typologies.add(typology);
            artist.setTypologies(typologies);
        }

        // Associa il work all'artista
        if (work instanceof PermanentWork) {
            artist.setPermanentWork((PermanentWork) work);
        } else if (work instanceof TemporaryWork) {
            artist.setTemporaryWork((TemporaryWork) work);
        } else if (work instanceof Retired) {
            artist.setRetired((Retired) work);
        } else if (work instanceof Unemployed) {
            artist.setUnemployed((Unemployed) work);
        } else if (work instanceof Student) {
            artist.setStudent((Student) work);
        } else {
            throw new IllegalArgumentException("Work not found");
        }

        // Associa il bank account all'artista
        if (bankAccount instanceof BankAccountIT) {
            artist.setBankAccountIT((BankAccountIT) bankAccount);
        } else if (bankAccount instanceof BankAccountES) {
            artist.setBankAccountES((BankAccountES) bankAccount);
        }

        // Salva l'artista con le relazioni
        artistRepository.save(artist);

    }

    @Override
    public void updateArtist(Long id, Artist artist) {

    }

    @Override
    public Optional<Artist> deleteArtist(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<Optional<Artist>> getAllArtists() {
        return null;
    }

    @Override
    public Long getArtistIdByUsername(String username) {
        return null;
    }
}
