package bonfiglio.scozzari.ing_soft.theatersoftware.service;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;

public interface TokenService {

    void addToken(String token, User user) throws UserNotFoundException;

    void deleteToken(String token);

}
