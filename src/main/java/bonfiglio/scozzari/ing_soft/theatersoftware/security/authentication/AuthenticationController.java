package bonfiglio.scozzari.ing_soft.theatersoftware.security.authentication;

import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.BadCredentialsException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) throws UserAlreadyExistsException, InvalidDataException {
        return ResponseEntity.ok(service.register(request));
    }

    /*@PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.registerAdmin(request));
    }*/

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) throws BadCredentialsException {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
