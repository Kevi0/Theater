package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Opera;
import org.springframework.stereotype.Component;

@Component
public class OperaRegistrationValidator {

    public void validate(Opera opera) throws InvalidDataException {

        validateTitle(opera.getTitle());
        validateStartDate(opera.getStartDate());
        validateStartRehearsal(opera.getStartRehearsal());

    }

    private void validateTitle(String title) throws InvalidDataException {

        if (title == null || title.isBlank()) {
            throw new InvalidDataException("Title cannot be null or empty");
        }
    }

    private void validateStartDate(java.time.LocalDate startDate) throws InvalidDataException {

        if (startDate == null) {
            throw new InvalidDataException("Start date cannot be null");
        }
    }

    private void validateStartRehearsal(java.time.LocalDate startRehearsal) throws InvalidDataException {

        if (startRehearsal == null) {
            throw new InvalidDataException("Start rehearsal cannot be null");
        }
    }

}
