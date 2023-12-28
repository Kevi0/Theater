package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> changePassword(String username, String newPassword) {
        if (userRepository.findUserByUsername(username).isPresent()) {
            User user = userRepository.findUserByUsername(username).get();
            user.setPassword(newPassword);
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteUser(Long id) {
        return Optional.empty();
    }

}
