package bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.play;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.ErrorMessage;
import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.agency.AgencyAlreadyDeletedException;
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
public class PlayExceptionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayExceptionManager.class);

    @ExceptionHandler(value = PlayAlreadyDeletedException.class)
    public ResponseEntity<ErrorMessage> handlePlayAlreadyDeletedException(AgencyAlreadyDeletedException exception, HandlerMethod handlerMethod) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.GONE.value());
        errorMessage.setError("GONE");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("AgencyAlreadyDeletedException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.GONE);

    }

    @ExceptionHandler(value = PlayAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> handlePlayAlreadyExistException(PlayAlreadyExistException exception, HandlerMethod handlerMethod) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.CONFLICT.value());
        errorMessage.setError("CONFLICT");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("AgencyAlreadyExistException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(value = PlayNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlePlayNotFoundException(PlayNotFoundException exception, HandlerMethod handlerMethod) {

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(LocalDateTime.now());
        errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
        errorMessage.setError("NOT FOUND");
        errorMessage.setMessage(exception.getMessage());
        errorMessage.setPath(RequestMappingUtils.extractPath(handlerMethod));

        LOGGER.error("AgencyNotFoundException: {}", exception.getMessage() + " Nella classe" +
                " ' " + RequestMappingUtils.extractClassName(handlerMethod) + " ' " + " con nome del metodo" +
                " ' " + RequestMappingUtils.extractMethodName(handlerMethod) + " ' ");

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

    }

}
