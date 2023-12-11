package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.PermanentWork;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.PermanentWorkRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.PermanentWorkService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PermanentWorkServiceImpl implements PermanentWorkService {

    private final PermanentWorkRepository permanentWorkRepository;

    @Override
    public Work addPermanentWork(PermanentWork permanentWork) {
        permanentWorkRepository.save(permanentWork);
        return permanentWork;
    }

    @Override
    public Optional<PermanentWork> deletePermanentWork(Long id) {
        return Optional.empty();
    }
}
