package bonfiglio.scozzari.ing_soft.theatersoftware.exception;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.RequestMappingUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class ExceptionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionManager.class);

    @ExceptionHandler(value = {DateTimeParseException.class})
    public ResponseEntity<ErrorMessage> handleDateTimeParseException(DateTimeParseException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage("Invalid date format");
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("DateTimeParseException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage("Invalid data");
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("HttpMessageNotReadableException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("IllegalArgumentException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<ErrorMessage> handleExpiredJwtException(ExpiredJwtException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage("Il toke JWT è scaduto!");
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("ExpiredJwtException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("BadCredentialsException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("AuthenticationException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.FORBIDDEN.value());
        errorMessage.setError("FORBIDDEN");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("AccessDeniedException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(value = {InvalidDataException.class})
    public ResponseEntity<ErrorMessage> handleInvalidDataException(InvalidDataException exception, HandlerMethod handlerMethod){

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError("BAD REQUEST");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("InvalidDataException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

    }

}
