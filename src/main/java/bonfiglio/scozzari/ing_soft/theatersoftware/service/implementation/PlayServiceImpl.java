package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Play;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.PlayRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.PlayService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PlayServiceImpl implements PlayService {

    private final PlayRepository playRepository;

    @Override
    public Optional<Play> addPlay(Play play, Long idOpera) {
        return Optional.empty();
    }

    public void addPlay(Play play) {
        playRepository.save(play);
    }

    @Override
    public Optional<Play> deletePlay(Long id) {
        return Optional.empty();
    }
}
