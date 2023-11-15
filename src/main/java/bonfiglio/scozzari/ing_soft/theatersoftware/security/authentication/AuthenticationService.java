package bonfiglio.scozzari.ing_soft.theatersoftware.security.authentication;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.enums.UserRoles;
import bonfiglio.scozzari.ing_soft.theatersoftware.repositories.UserRepository;
import bonfiglio.scozzari.ing_soft.theatersoftware.security.configuration.JwtService;
import bonfiglio.scozzari.ing_soft.theatersoftware.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .taxCode(request.getTaxCode())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRoles.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
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

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
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
