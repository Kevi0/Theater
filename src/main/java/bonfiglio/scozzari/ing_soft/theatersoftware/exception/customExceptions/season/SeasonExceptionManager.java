package bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.season;

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

@SuppressWarnings("All")
@RestControllerAdvice
public class SeasonExceptionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeasonExceptionManager.class);

    @ExceptionHandler(value = {SeasonNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleSeasonNotFoundException(SeasonNotFoundException exception, HandlerMethod handlerMethod) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorMessage.setError("NOT FOUND");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("SeasonNotFoundException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = {SeasonAlreadyExistException.class})
    public ResponseEntity<ErrorMessage> handleSeasonAlreadyExistException(SeasonAlreadyExistException exception, HandlerMethod handlerMethod) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.CONFLICT.value());
        errorMessage.setError("CONFLICT");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("SeasonAlreadyExistException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {SeasonAlreadyDeletedException.class})
    public ResponseEntity<ErrorMessage> handleSeasonAlreadyDeletedException(SeasonAlreadyDeletedException exception, HandlerMethod handlerMethod) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.GONE.value());
        errorMessage.setError("GONE");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("SeasonAlreadyDeletedException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.GONE);
    }

}
