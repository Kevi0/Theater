package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.play.PlayAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.play.PlayNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Play;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.PlayRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.PlayService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.PlayRegistrationValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PlayServiceImpl implements PlayService {

    private final PlayRepository playRepository;

    private final PlayRegistrationValidator validator;

    @Override
    public void addPlay(Play play, Long idOpera) {

    }

    @Override
    public void addPlay(Play play) throws PlayAlreadyExistException, InvalidDataException {

        if (playRepository.findById(play.getId()).isEmpty()) {

            validator.validate(play);

            var playToInsert = Play.builder()
                    .name(play.getName())
                    .datePlay(play.getDatePlay())
                    .build();
            playToInsert.setCreatedAt(LocalDateTime.now());
            playRepository.save(playToInsert);

        } else {
            throw new PlayAlreadyExistException("Error when entering the play");
        }

    }

    @Override
    public void updatePlay(Long id, Play play) throws InvalidDataException, IllegalAccessException, PlayNotFoundException {

        Optional<Play> playToUpdate = playRepository.findById(id);

        if (playToUpdate.isPresent()) {

            validator.validate(play);
            Play existingPlay = playToUpdate.get();
            ObjectUpdater<Play> playUpdater = new ObjectUpdater<>();
            playRepository.save(playUpdater.updateObject(existingPlay, play));

        } else {
            throw new PlayNotFoundException("Error when updating the play");
        }

    }

    @Override
    public Optional<Play> deletePlay(Long id) throws PlayNotFoundException {

        Optional<Play> playToDelete = playRepository.findById(id);

        if (playToDelete.isPresent()) {

            Play existingPlay = playToDelete.get();
            playRepository.delete(existingPlay);

        } else {
            throw new PlayNotFoundException("Error when deleting the play");
        }

        return playToDelete;

    }

    @Override
    public List<Play> getAllPlays() {
        try {
            return playRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error when getting all the plays");
        }
    }

    @Override
    public Long getPlayIdByName(String name) throws PlayNotFoundException {
        return playRepository.findByName(name)
                .map(Play::getId)
                .orElseThrow(() -> new PlayNotFoundException("Error when getting the play id by name"));
    }
}
