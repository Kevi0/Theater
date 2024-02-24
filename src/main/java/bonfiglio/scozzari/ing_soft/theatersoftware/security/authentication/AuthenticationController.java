package bonfiglio.scozzari.ing_soft.theatersoftware.security.authentication;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserAuthenticationDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.user.UserRegistrationDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.user.UserAuthenticationMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.user.UserRegistrationMapper;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.SendingMailException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.BadCredentialsException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UnregisteredUserException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.user.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService userService;

    private final UserRegistrationMapper userRegistrationMapper;
    private final UserAuthenticationMapper userAuthenticationMapper;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody InputDTO userDTO
    ) throws UserAlreadyExistException, InvalidDataException, SendingMailException, UnregisteredUserException {

        try {
            if (userDTO instanceof UserRegistrationDTO) {
                AuthenticationResponse response = userService.register(userRegistrationMapper.userDTOToUser(userDTO));

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("User not registered");
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Invalid data");
        }
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody InputDTO userDTO
    ) throws BadCredentialsException {

        try {
            if (userDTO instanceof UserAuthenticationDTO) {
                AuthenticationResponse response = userService.authenticate(userAuthenticationMapper.userDTOToUser(userDTO));

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("User non authenticated");
            }
        } catch (JsonParseException e) {
            throw new HttpMessageNotReadableException("Invalid data");
        }
    }

}
