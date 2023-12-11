package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.observer.Observers;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.observer.Publisher;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.BankAccount;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.ArtistRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.TypologyRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.ArtistService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class
ArtistServiceImpl implements ArtistService {

    private final UserRepository userRepository;
    private final TypologyRepository typologyRepository;
    private final ArtistRepository artistRepository;

    @Override
    public Optional<Artist> addArtist(
            Artist artist,
            Long idUser,
            Set<String> idTypologies,
            Work work,
            BankAccount bankAccount
    ) throws Exception {

        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty())
            throw new Exception("User not found!"); //TODO Custom UserNotFoundException
        artist.setUser(user.get());

        for(String idTypology : idTypologies){

            Typology typology = typologyRepository.findById(idTypology)
                    .orElseThrow(() -> new EntityNotFoundException("Typology not found with ID: " + idTypology)); //TODO Custom TypologyNotFoundException

            Set<Typology> typologies = artist.getTypologies() != null ? artist.getTypologies() : new HashSet<>();
            typologies.add(typology);
            artist.setTypologies(typologies);
        }

        if(work instanceof PermanentWork){
            artist.setPermanentWork((PermanentWork) work);
        }
        else if(work instanceof Retired){
            artist.setRetired((Retired) work);
        }
        else if(work instanceof Student){
            artist.setStudent((Student) work);
        }
        else if(work instanceof TemporaryWork){
            artist.setTemporaryWork((TemporaryWork) work);
        }
        else if(work instanceof Unemployed){
            artist.setUnemployed((Unemployed) work);
        }
        else {
            throw new Exception("Work not found!"); //TODO Custom WorkNotFoundException
        }

        if(bankAccount instanceof BankAccountIT){
            ((BankAccountIT) bankAccount).setArtist(artist);
        }
        else if(bankAccount instanceof BankAccountES){
            ((BankAccountES) bankAccount).setArtist(artist);
        }
        else {
            throw new Exception("Bank account not found!"); //TODO Custom BankAccountNotFoundException
        }

        return Optional.of(artistRepository.save(artist));

    }

    public Optional<Artist> updateNameOfArtist(){
        List<Observers> theaters = List.of(); //TODO QUERY
        Publisher.notifyObservers(theaters);
        return null;
    }

    @Override
    public Optional<Artist> deleteArtist(Long id) {
        return Optional.empty();
    }
}
