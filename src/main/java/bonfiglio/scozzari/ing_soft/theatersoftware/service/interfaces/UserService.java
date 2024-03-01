package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void updateUser(Long id, User user) throws IllegalAccessException, UserNotFoundException, InvalidDataException;

    void updatePassword(Long id, String password) throws InvalidDataException;

    Optional<User> deleteUser(Long id) throws UserAlreadyDeletedException, UserNotFoundException;

    List<User> getAllUsers();

    Long getUserIdByUsername(String username) throws UserNotFoundException;

    Optional<User> getUserByUsername(String username);

}
