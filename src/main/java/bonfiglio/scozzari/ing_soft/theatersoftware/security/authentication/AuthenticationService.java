package bonfiglio.scozzari.ing_soft.theatersoftware.security.authentication;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.InvalidTokenException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.SendingMailException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.BadCredentialsException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UnregisteredUserException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserNotFoundException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.UserRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.TokenRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.security.configuration.JwtService;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.UserService;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.implementation.TokenServiceImpl;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.mail.EmailService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.UserRegistrationValidator;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.username.GenerateUsername;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
    private final TokenRepository tokenRepository;
    private final UserService userService;
    private final TokenServiceImpl tokenService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    private final UserRegistrationValidator validator;

    private final GenerateUsername generateUsername;

    public void register(User user) throws UserAlreadyExistException, InvalidDataException, SendingMailException, UnregisteredUserException {

        if (!(userRepository.existsByEmail(user.getEmail()))) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                throw new UnregisteredUserException("La registrazione richiede l'autenticazione.");
            }

            String currentUsername = authentication.getName();

            validator.validate(user);

            var userToInsert = User.builder()
                    .name(user.getName())
                    .surname(user.getSurname())
                    .email(user.getEmail())
                    .username(generateUsername.generateUniqueUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role(UserRoles.USER)
                    .build();
            userToInsert.setCreatedAt(LocalDateTime.now());
            userToInsert.setCreatedBy(currentUsername);

            try {

                userRepository.save(userToInsert);

            } catch (ConstraintViolationException | DataIntegrityViolationException | TransientObjectException | LockAcquisitionException e){
                throw new UnregisteredUserException("Errore durante la registrazione: qualcosa è andato storto!");
            }

            sendRegistrationEmail(userToInsert);

        } else {
            throw new UserAlreadyExistException("Errore durante la registrazione!");
        }
    }

    public void registerAdmin(User user) throws UnregisteredUserException, InvalidDataException, SendingMailException, UserAlreadyExistException {

        if (!(userRepository.existsByEmail(user.getEmail()))) {

            /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
                throw new UnregisteredUserException("La registrazione richiede l'autenticazione.");
            }*/

            //String currentUsername = authentication.getName();

            validator.validate(user);

            var userToInsert = User.builder()
                    .name(user.getName())
                    .surname(user.getSurname())
                    .email(user.getEmail())
                    .username(generateUsername.generateUniqueUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .role(UserRoles.ADMIN)
                    .build();
            userToInsert.setCreatedAt(LocalDateTime.now());
            //userToInsert.setCreatedBy(currentUsername);

            try {

                userRepository.save(userToInsert);

            } catch (ConstraintViolationException | DataIntegrityViolationException | TransientObjectException | LockAcquisitionException e){
                throw new UnregisteredUserException("Errore durante la registrazione: qualcosa è andato storto!");
            }

            sendRegistrationEmail(userToInsert);

        } else {
            throw new UserAlreadyExistException("Errore durante la registrazione!");
        }

    }

    public AuthenticationResponse authenticate(User user) throws BadCredentialsException, UserNotFoundException, InvalidTokenException, ExpiredJwtException {

        var userToAuthenticate = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Errore durante l'autenticazione: credenziali errate!"));

        if (!passwordEncoder.matches(user.getPassword(), userToAuthenticate.getPassword()))
            throw new BadCredentialsException("Errore durante l'autenticazione: credenziali errate!");

        if (tokenRepository.existsByUser(userToAuthenticate)) {

            String token = tokenRepository.findTokenByUser(userToAuthenticate);

                if (jwtService.isTokenValid(token, userToAuthenticate)) {

                    if (jwtService.isTokenExpired(token)) {

                        jwtService.handleExpiredJwtException(token);

                    } else {

                        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        user.getUsername(),
                                        user.getPassword()
                                )
                        );

                        return AuthenticationResponse
                                .builder()
                                .token(token)
                                .build();

                    }

                } else {
                    throw new InvalidTokenException("Token non valido");
                }
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(userToAuthenticate);
        tokenService.addToken(jwtToken, userToAuthenticate);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();

    }


    private void sendRegistrationEmail(User user) throws SendingMailException {

        Map<String, Object> emailModel = new HashMap<>();
        emailModel.put("subject", "Registrazione utente");
        emailModel.put("name", user.getName());
        emailModel.put("username", user.getUsername());

        emailService.sendRegistrationEmail(user.getEmail(), (String) emailModel.get("subject"), null, null, "user-registration-email", emailModel);

    }

    public Optional<User> getAuthenticationUser() throws UserNotFoundException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUsername(authentication.getName());

    }

}
