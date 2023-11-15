package bonfiglio.scozzari.ing_soft.theatersoftware.services.implementations;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }
}
