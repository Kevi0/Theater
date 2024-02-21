package bonfiglio.scozzari.ing_soft.theatersoftware.security.authentication;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.BadCredentialsException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.UserRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.security.configuration.JwtService;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.UserService;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.mail.EmailService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.UserRegistrationValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    private final UserRegistrationValidator validator;

    public AuthenticationResponse register(User user) throws UserAlreadyExistException, InvalidDataException {

        if (userRepository.findUserByUsername(user.getUsername()).isEmpty()) {

            validator.validate(user);

            var userToInsert = User.builder()
                    .name(user.getName())
                    .surname(user.getSurname())
                    .taxCode(user.getTaxCode())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role(UserRoles.USER)
                    .build();
            userToInsert.setCreatedAt(LocalDateTime.now());

            try {

                userRepository.save(userToInsert);

                Map<String, Object> emailModel = new HashMap<>();
                emailModel.put("subject", "Registrazione utente");
                emailModel.put("name", user.getName());

                emailService.sendRegistrationEmail(user.getEmail(), (String) emailModel.get("subject"), null, null, "user-registration-email", emailModel);

                var jwtToken = jwtService.generateToken(user);

                return AuthenticationResponse
                        .builder()
                        .token(jwtToken)
                        .build();

            } catch (Exception e) {
                throw new UserAlreadyExistException("Errore durante la registrazione!");
            }

        } else {
            throw new UserAlreadyExistException("Errore durante la registrazione!");
        }
    }


    public AuthenticationResponse authenticate(User user) throws BadCredentialsException {

        var userToAuthenticate = userRepository.findUserByUsername(user.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Errore durante l'autenticazione: credenziali errate!"));

        if (!passwordEncoder.matches(user.getPassword(), userToAuthenticate.getPassword()))
            throw new BadCredentialsException("Errore durante l'autenticazione: credenziali errate!");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(userToAuthenticate);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public Optional<User> getAuthenticationUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUsername(authentication.getName());
    }
}
