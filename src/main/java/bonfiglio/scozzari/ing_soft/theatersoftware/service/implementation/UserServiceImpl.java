package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserSummaryDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.DataAccessServiceException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
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

    private final PasswordEncoder passwordEncoder;

    private final UserRegistrationValidator validator;


    @Override
    public void updateUser(Long id, User user) throws IllegalAccessException, UserNotFoundException, InvalidDataException {

        Optional<User> userToUpdate = userRepository.findById(id);

        if (userToUpdate.isPresent()) {

            validator.validateUpdate(user);
            User existingUser = userToUpdate.get();
            ObjectUpdater<User> userUpdater = new ObjectUpdater<>();
            userRepository.save(userUpdater.updateObject(existingUser, user));

            //TODO SEND EMAIL

        } else {
            throw new UserNotFoundException("Errore durante l'aggiornamento dei dati dell'utente!");
        }

    }

    @Override
    public void updatePassword(Long id, String password) throws InvalidDataException, UserNotFoundException {

        Optional<User> userToUpdate = userRepository.findById(id);

        if (userToUpdate.isPresent()) {

            User existingUser = userToUpdate.get();
            if (passwordEncoder.matches(password, existingUser.getPassword())) {

                throw new InvalidDataException("Errore durante l'aggiornamento della password!");

            } else {

                existingUser.setPassword(passwordEncoder.encode(password));
                userRepository.save(existingUser);

            }

        } else {
            throw new UserNotFoundException("Errore durante l'aggiornamento della password!");
        }

    }

    @Override
    public Optional<User> deleteUser(Long id) throws UserNotFoundException {

        Optional<User> userToDelete = userRepository.findById(id);

        if (userToDelete.isPresent()) {
            User existingUser = userToDelete.get();
            userRepository.delete(existingUser);

        } else {
            throw new UserNotFoundException("Errore durante l'eliminazione dell'utente!");
        }

        return userToDelete;

    }

    @Override
    public List<UserSummaryDTO> getAllUsers() throws DataAccessServiceException {
        try {
            List<User> users = userRepository.findAll();
            return users.stream()
                    .map(UserSummaryDTO::new)
                    .toList();
        } catch (DataAccessException e) {
            throw new DataAccessServiceException("Errore durante il recupero degli utenti!");
        }
    }

    @Override
    public Long getUserIdByUsername(String username) throws UserNotFoundException {
        return userRepository.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new UserNotFoundException("Errore durante il recupero dell'utente!"));
    }

    @Override
    public Optional<User> getUserByUsername(String username) throws UserNotFoundException {
        return Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Errore durante il recupero dell'utente!")));
    }


}
