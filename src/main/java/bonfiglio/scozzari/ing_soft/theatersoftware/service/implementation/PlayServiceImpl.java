package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Play;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.PlayService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PlayServiceImpl implements PlayService {
    @Override
    public Optional<Play> addPlay(Play play, Long idOpera) {
        return Optional.empty();
    }

    @Override
    public Optional<Play> deletePlay(Long id) {
        return Optional.empty();
    }
}
