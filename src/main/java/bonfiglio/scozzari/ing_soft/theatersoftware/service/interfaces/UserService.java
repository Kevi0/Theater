package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUsername(String username);

    //TODO UPDATE

    Optional<User> changePassword(String username, String newPassword) throws Exception;

    Optional<User> deleteUser(String username) throws Exception;

}
