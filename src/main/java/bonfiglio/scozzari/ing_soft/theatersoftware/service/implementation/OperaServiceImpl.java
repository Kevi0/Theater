package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Opera;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Season;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Venue;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.OperaRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.SeasonRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.VenueRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.OperaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OperaServiceImpl implements OperaService {

    private final VenueRepository venueRepository;

    private final SeasonRepository seasonRepository;

    private final OperaRepository operaRepository;

    @Override
    public Optional<Opera> addOpera(
            Opera opera,
            Long idVenue,
            Long idSeason
    ) throws Exception {
        Optional<Venue> venue = venueRepository.findById(idVenue);
        Optional<Season> season = seasonRepository.findById(idSeason);

        if(venue.isEmpty())
            throw new Exception("Venue not found!"); //TODO Custom VenueNotFoundException

        if(season.isEmpty())
            throw new Exception("Season not found!"); //TODO Custom SeasonNotFoundException

        opera.setVenue(venue.get());
        opera.getSeasons().add(season.get());
        return Optional.of(operaRepository.save(opera));
    }

    @Override
    public Optional<Opera> deleteOpera(Long id) {
        return Optional.empty();
    }

    public void addOpera(Opera opera) {

        operaRepository.save(opera);

    }
}
