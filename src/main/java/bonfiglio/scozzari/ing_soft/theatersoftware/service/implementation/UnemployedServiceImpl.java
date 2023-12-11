package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Unemployed;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.PermanentWorkRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.UnemployedRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.UnemployedService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UnemployedServiceImpl implements UnemployedService {

    private final UnemployedRepository unemployedRepository;

    @Override
    public Work addUnemployed(Unemployed unemployed) {
        unemployedRepository.save(unemployed);
        return unemployed;
    }

    @Override
    public Optional<Unemployed> deleteUnemployed(Long id) {
        return Optional.empty();
    }
}
