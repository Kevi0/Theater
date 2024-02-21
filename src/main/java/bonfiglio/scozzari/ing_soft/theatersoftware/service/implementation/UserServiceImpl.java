package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.UserService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.UserRegistrationValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserRegistrationValidator validator;


    @Override
    public void updateUser(Long id, User user) throws IllegalAccessException, UserNotFoundException, InvalidDataException {
        Optional<User> userToUpdate = userRepository.findById(id);

        if (userToUpdate.isPresent() && (!userRepository.checkIfUserIsDeleted(id))) {

            validator.validate(user);
            User existingUser = userToUpdate.get();
            ObjectUpdater<User> userUpdater = new ObjectUpdater<>();

            userRepository.save(userUpdater.updateObject(existingUser, user));
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

        } else {
            throw new UserNotFoundException("Error when updating the user");

        }
    }

    @Override
    public Optional<User> deleteUser(Long id) throws UserAlreadyDeletedException, UserNotFoundException {

        Optional<User> userToDelete = userRepository.findById(id);

        if (userToDelete.isPresent()) {
            User existingUser = userToDelete.get();

            if (existingUser.getDeletedAt() == null) {
                userRepository.deleteUserById(id);
                return Optional.of(existingUser);

            } else {
                throw new UserAlreadyDeletedException("Error when deleting the user");
            }
        } else {
            throw new UserNotFoundException("Error when deleting the user");
        }

    }

    @Override
    public Set<Optional<User>> getAllUsers() {
        return new HashSet<>(userRepository.findAllUsers());
    }

    @Override
    public Long getUserIdByUsername(String username) throws UserNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new UserNotFoundException("Error when getting the user id by username"));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
