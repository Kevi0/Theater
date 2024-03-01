package bonfiglio.scozzari.ing_soft.theatersoftware.utils.username;

import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
public class GenerateUsername {

    private final UserRepository userRepository;

    public String generateUniqueUsername() {

        Random random = new Random();
        String username;

        do {

            int randomNumber = 1 + random.nextInt(43046721);
            username = String.format("%08d", randomNumber);

        } while (usernameExists(username));

        return username;

    }

    private boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

}
