package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Season;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.SeasonRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.TheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.SeasonService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final TheaterRepository theaterRepository;

    private final SeasonRepository seasonRepository;

    @Override
    public Optional<Season> addSeason(Season season, Long idTheater) throws Exception {
        Optional<Theater> theater = theaterRepository.findById(idTheater);
        if(theater.isEmpty())
            throw new Exception("Theater not found!"); //TODO Custom TheaterNotFoundException
        else {
            if(seasonRepository.findById(season.getId()).isPresent())
                throw new Exception("Season already exist"); //TODO Custom SeasonAlreadyExistException
            season.setTheater(theater.get());
            return Optional.of(seasonRepository.save(season));
        }
    }

    public void addSeason(Season season) throws Exception {
        if(seasonRepository.findById(season.getId()).isPresent())
            throw new Exception("Season already exist"); //TODO Custom SeasonAlreadyExistException
        seasonRepository.save(season);
    }

    @Override
    public Optional<Season> deleteSeason(Long id) {
        return Optional.empty();
    }
}
