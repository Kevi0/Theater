package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    void updateUser(Long id, User user) throws IllegalAccessException, UserNotFoundException, InvalidDataException;

    Optional<User> deleteUser(Long id) throws UserAlreadyDeletedException, UserNotFoundException;

    Set<Optional<User>> getAllUsers();

    Long getUserIdByUsername(String username) throws UserNotFoundException;

    Optional<User> getUserByUsername(String username);

}
