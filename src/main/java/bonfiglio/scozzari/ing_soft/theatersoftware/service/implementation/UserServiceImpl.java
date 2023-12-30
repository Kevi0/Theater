package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> changePassword(String username, String newPassword) throws Exception {
        if (!(userRepository.findUserByUsername(username).isEmpty())){
            if (passwordEncoder.matches(newPassword, userRepository.findUserByUsername(username).get().getPassword())){
                throw new Exception("La nuova password non può essere uguale a password utilizzate in precedenza!"); //TODO: Custom ErrorChangePasswordException
            } else {
                userRepository.findUserByUsername(username).get().setPassword(passwordEncoder.encode(newPassword));
                return userRepository.findUserByUsername(username);
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> deleteUser(String username) throws Exception {
        if (!(userRepository.findUserByUsername(username).isEmpty())){
            userRepository.delete(userRepository.findUserByUsername(username).get());
            return userRepository.findUserByUsername(username);
        } else {
            return Optional.empty();
        }
    }

}
