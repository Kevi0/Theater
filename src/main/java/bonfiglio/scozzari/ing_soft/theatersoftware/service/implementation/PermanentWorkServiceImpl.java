package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.PermanentWork;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.PermanentWorkRepository;
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
