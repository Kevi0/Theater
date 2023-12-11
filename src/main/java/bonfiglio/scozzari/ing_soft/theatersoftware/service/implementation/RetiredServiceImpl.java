package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Retired;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.PermanentWorkRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.RetiredRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.RetiredService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class RetiredServiceImpl implements RetiredService {

    private final RetiredRepository retiredRepository;

    @Override
    public Work addRetired(Retired retired) {
        retiredRepository.save(retired);
        return retired;
    }

    @Override
    public Optional<Retired> deleteRetired(Long id) {
        return Optional.empty();
    }
}
