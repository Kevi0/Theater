package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.TheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.TheaterService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;

    @Override
    public Optional<Theater> addTheater(Theater theater) throws Exception {
        if(theaterRepository.findTheaterByName(theater.getName()).isEmpty()){
            return Optional.of(theaterRepository.save(theater));
        } else {
            throw new Exception(); //TODO Custom TheaterNotFoundException
        }
    }

    @Override
    public Optional<Theater> deleteTheater(Long id) {
        return Optional.empty();
    }
}
