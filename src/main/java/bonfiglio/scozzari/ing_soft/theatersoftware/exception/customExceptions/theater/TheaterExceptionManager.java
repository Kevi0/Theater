package bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.theater;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.ErrorMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.RequestMappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TheaterExceptionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TheaterExceptionManager.class);

    @ExceptionHandler(value = {UnregisteredTheaterException.class})
    public ResponseEntity<ErrorMessage> handleUnregisteredTheaterException(UnregisteredTheaterException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorMessage.setError("UNAUTHORIZED");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("UnregisteredTheaterException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {TheaterAlreadyDeletedException.class})
    public ResponseEntity<ErrorMessage> handleTheaterAlreadyDeletedException(TheaterAlreadyDeletedException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.GONE.value());
        errorMessage.setError("GONE");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("TheaterAlreadyDeletedException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.GONE);
    }

    @ExceptionHandler(value = {TheaterAlreadyExistException.class})
    public ResponseEntity<ErrorMessage> handleTheaterAlreadyExistException(TheaterAlreadyExistException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.CONFLICT.value());
        errorMessage.setError("CONFLICT");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("TheaterAlreadyExistException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {TheaterNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleTheaterNotFoundException(TheaterNotFoundException exception, HandlerMethod handlerMethod){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorMessage.setError("NOT FOUND");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("TheaterNotFoundException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
