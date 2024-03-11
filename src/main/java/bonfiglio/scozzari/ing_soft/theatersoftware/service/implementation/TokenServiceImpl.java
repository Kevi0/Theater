package bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Token;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.TokenRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;


    @Override
    public void addToken(String token, User user) throws UserNotFoundException {

        Optional<User> extractUser = userRepository.findById(user.getId());

        if (extractUser.isPresent()) {
            tokenRepository.save(new Token(token, extractUser.get()));
        } else {
            throw new UserNotFoundException("Error when adding the token");
        }

    }

    @Override
    public void deleteToken(String token) {

        tokenRepository.deleteByToken(token);

    }
}
