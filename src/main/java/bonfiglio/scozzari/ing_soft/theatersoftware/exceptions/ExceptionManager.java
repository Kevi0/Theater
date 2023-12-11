package bonfiglio.scozzari.ing_soft.theatersoftware.exceptions;

import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.BadCredentialsException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.UserAlreadyExistsException;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionManager {

    //TODO Specify the path of the API involved in the exception

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionManager.class);

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ErrorMessage> handleExpiredJwtException(ExpiredJwtException exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage("Il toke JWT è scaduto!");
        errorMessage.setPath("/api"); //Specifica il percorso dell'API coinvolto

        LOGGER.error("ExpiredJwtException: {}", exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath("/api"); //Specifica il percorso dell'API coinvolto

        LOGGER.error("BadCredentialsException: {}", exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<ErrorMessage> handleUserAlreadyExistsException(UserAlreadyExistsException exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.CONFLICT.value());
        errorMessage.setError("CONFLICT");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath("/api"); //Specifica il percorso dell'API coinvolto

        LOGGER.error("UserAlreadyExistsException: {}", exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath("/api"); //Specifica il percorso dell'API coinvolto

        LOGGER.error("AuthenticationException: {}", exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.FORBIDDEN.value());
        errorMessage.setError("FORBIDDEN");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath("/api"); //Specifica il percorso dell'API coinvolto

        LOGGER.error("AccessDeniedException: {}", exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {InvalidDataException.class})
    public ResponseEntity<ErrorMessage> handleInvalidDataException(InvalidDataException exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath("/api"); //Specifica il percorso dell'API coinvolto

        LOGGER.error("InvalidDataException: {}", exception.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
