package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.TemporaryWork;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.TemporaryWorkRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.TemporaryWorkService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TemporaryWorkServiceImpl implements TemporaryWorkService {

    private final TemporaryWorkRepository temporaryWorkRepository;

    @Override
    public Work addTemporaryWork(TemporaryWork temporaryWork) {
        temporaryWorkRepository.save(temporaryWork);
        return temporaryWork;
    }

    @Override
    public Optional<TemporaryWork> deleteTemporaryWork(Long id) {
        return Optional.empty();
    }
}
