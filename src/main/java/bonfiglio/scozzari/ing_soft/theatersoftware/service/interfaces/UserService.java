package bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUsername(String username);

    //TODO UPDATE

    Optional<User> deleteUser(Long id);

}
