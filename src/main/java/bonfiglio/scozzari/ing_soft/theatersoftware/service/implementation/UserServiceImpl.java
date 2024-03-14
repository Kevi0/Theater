package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserSummaryDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.DataAccessServiceException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserTheater;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserTheaterRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.UserService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.ObjectUpdater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.UserRegistrationValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTheaterRepository userTheaterRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRegistrationValidator validator;


    @Override
    public void updateUser(Long id, User user) throws IllegalAccessException, UserNotFoundException, InvalidDataException {

        Optional<User> userToUpdate = userRepository.findById(id);

        if (userToUpdate.isPresent() && (!userRepository.existsByIdAndDeletedAtIsNotNull(id))) {

            validator.validateUpdate(user);
            User existingUser = userToUpdate.get();
            ObjectUpdater<User> userUpdater = new ObjectUpdater<>();
            userRepository.save(userUpdater.updateObject(existingUser, user));

        } else {
            throw new UserNotFoundException("Error when updating the user");
        }

    }

    @Override
    public void updatePassword(Long id, String password) throws InvalidDataException {

        Optional<User> userToUpdate = userRepository.findById(id);

        if (userToUpdate.isPresent() && (!userRepository.existsByIdAndDeletedAtIsNotNull(id))) {

            User existingUser = userToUpdate.get();
            if (passwordEncoder.matches(password, existingUser.getPassword())) {

                throw new InvalidDataException("Error when updating the password");

            } else {

                existingUser.setPassword(passwordEncoder.encode(password));
                userRepository.save(existingUser);

            }

        }

    }

    @Override
    public Optional<User> deleteUser(Long id) throws UserAlreadyDeletedException, UserNotFoundException {

        Optional<User> userToDelete = userRepository.findById(id);

        if (userToDelete.isPresent()) {
            User existingUser = userToDelete.get();

            if (existingUser.getDeletedAt() == null) {
                userRepository.softDeleteById(id);
                removeUserRelations(existingUser);
                return Optional.of(existingUser);

            } else {
                throw new UserAlreadyDeletedException("Error when deleting the user");
            }
        } else {
            throw new UserNotFoundException("Error when deleting the user");
        }

    }

    @Override
    public List<UserSummaryDTO> getAllUsers() throws DataAccessServiceException {
        try {
            List<User> users = userRepository.findAllByDeletedAtIsNull();
            return users.stream()
                    .map(UserSummaryDTO::new)
                    .toList();
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Error when getting all the users");
        }
    }

    @Override
    public Long getUserIdByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new UserNotFoundException("Error when getting the user id by username"));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void removeUserRelations(User user) {

        List<UserTheater> userTheaters = userTheaterRepository.findByUser(user);
        userTheaters.forEach(userTheater -> userTheaterRepository.deleteByUser(userTheater.getUser()));

    }
}
