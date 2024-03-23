package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserSummaryDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.DataAccessServiceException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserAlreadyDeletedException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void updateUser(Long id, User user) throws IllegalAccessException, UserNotFoundException, InvalidDataException;

    void updatePassword(Long id, String password) throws InvalidDataException, UserNotFoundException;

    Optional<User> deleteUser(Long id) throws UserAlreadyDeletedException, UserNotFoundException;

    List<UserSummaryDTO> getAllUsers() throws DataAccessServiceException;

    Long getUserIdByUsername(String username) throws UserNotFoundException;

    Optional<User> getUserByUsername(String username) throws UserNotFoundException;

}
