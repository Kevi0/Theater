package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Unemployed;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UnemployedRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.UnemployedService;
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
