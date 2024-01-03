package bonfiglio.scozzari.ing_soft.theatersoftware.security.authentication;

import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.BadCredentialsException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.UserAlreadyExistsException;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.enums.UserRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.security.configuration.JwtService;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.interfaces.UserService;
import bonfiglio.scozzari.ing_soft.theatersoftware.service.mail.EmailService;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.RegistrationValidator;
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

    private final RegistrationValidator validator;

    public AuthenticationResponse register(
            RegisterRequest request
    ) throws UserAlreadyExistsException, InvalidDataException {

        if (userService.getUserByUsername(request.getUsername()).isPresent())
            throw new UserAlreadyExistsException("Error during registration!");

        validator.validate(request);

        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .taxCode(request.getTaxCode())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .role(UserRoles.USER)
                .build();

        try {

            userRepository.save(user);

            Map<String, Object> emailModel = new HashMap<>();
            emailModel.put("subject", "Registrazione utente");
            emailModel.put("name", user.getName());

            emailService.sendRegistrationEmail(user.getEmail(), (String) emailModel.get("subject"), null, null, "registration-email", emailModel);

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse
                    .builder()
                    .token(jwtToken)
                    .build();

        }catch (Exception e) {
            e.printStackTrace();
            throw new UserAlreadyExistsException("Errore durante la registrazione!");
        }

        /*Map<String, Object> emailModel = new HashMap<>();
        emailModel.put("subject", "Registrazione utente");
        emailModel.put("name", user.getName());

        emailService.sendRegistrationEmail(user.getEmail(), (String) emailModel.get("subject"),  null, null, "registration-email", emailModel);

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();*/
    }

    /*public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .taxCode(request.getTaxCode())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRoles.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }*/

    public AuthenticationResponse authenticate(
            AuthenticationRequest request
    ) throws BadCredentialsException {

        //TODO Implements other custom exceptions

        var user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Errore durante l'autenticazione: credenziali errate!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Errore durante l'autenticazione: credenziali errate!");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user);

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
