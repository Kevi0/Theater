package bonfiglio.scozzari.ing_soft.theatersoftware.excpetion;

import bonfiglio.scozzari.ing_soft.theatersoftware.excpetion.customException.InvalidDatesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(InvalidDatesException.class)
    public ResponseEntity<ErrorMessage> handleInvalidDatesException(InvalidDatesException ex) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
