package bonfiglio.scozzari.ing_soft.theatersoftware.security.authentication;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.SendingMailException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.BadCredentialsException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UnregisteredUserException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserAlreadyExistException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.UserRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.repository.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.security.configuration.JwtService;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.UserService;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.mail.EmailService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.UserRegistrationValidator;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.username.GenerateUsername;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.hibernate.TransientObjectException;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
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

@SuppressWarnings("All")
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

    private final GenerateUsername generateUsername;

    public AuthenticationResponse register(User user) throws UserAlreadyExistException, InvalidDataException, SendingMailException, UnregisteredUserException {

        if (!userRepository.findByUsername(user.getUsername())) {

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

            try {

                userRepository.save(userToInsert);
                sendRegistrationEmail(userToInsert);

                var jwtToken = jwtService.generateToken(user);

                return AuthenticationResponse
                        .builder()
                        .token(jwtToken)
                        .build();

            } catch (ConstraintViolationException e) {
                throw new UserAlreadyExistException("Errore durante la registrazione!");
            } catch (DataIntegrityViolationException e) {
                throw new InvalidDataException("Errore durante la registrazione, problemi con l'itegrità dei dati!");
            } catch (TransientObjectException | LockAcquisitionException e) {
                throw new UnregisteredUserException("Errore durante la registrazione, utente non registrato!");
            }

        } else if (userRepository.findByUsernameAndDeletedAtIsNull(user.getUsername())) {

            Optional<User> userToUpdate = userRepository.findUserByUsername(user.getUsername());
            User existingUser = userToUpdate.get();
            existingUser.setCreatedAt(LocalDateTime.now());
            existingUser.setDeletedAt(null);

            try {

                userRepository.save(existingUser);
                sendRegistrationEmail(existingUser);

                var jwtToken = jwtService.generateToken(user);

                return AuthenticationResponse
                        .builder()
                        .token(jwtToken)
                        .build();

            } catch (MailException e) {
                throw new SendingMailException("Errore durante l'invio della mail!");
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

    private void sendRegistrationEmail(User user) throws SendingMailException {

        Map<String, Object> emailModel = new HashMap<>();
        emailModel.put("subject", "Registrazione utente");
        emailModel.put("name", user.getName());
        emailModel.put("username", user.getUsername());
        emailModel.put("password", user.getPassword());

        emailService.sendRegistrationEmail(user.getEmail(), (String) emailModel.get("subject"), null, null, "user-registration-email", emailModel);

    }

    public Optional<User> getAuthenticationUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUsername(authentication.getName());

    }

}
