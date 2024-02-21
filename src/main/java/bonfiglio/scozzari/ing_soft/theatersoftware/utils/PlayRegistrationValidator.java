package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Play;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Component
public class PlayRegistrationValidator {

    public void validate(Play play) throws InvalidDataException {

        validateName(play.getName());
        validateDatePlay(String.valueOf(play.getDatePlay()));

    }

    private void validateName(String name) throws InvalidDataException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Error when entering the play: the 'name' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateDatePlay(String datePlay) throws InvalidDataException {

        if (datePlay == null || datePlay.trim().isEmpty()) {
            throw new InvalidDataException("Error when entering the play: the 'datePlay' field cannot be empty, or cannot contain only spaces!");
        }

        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(datePlay);

            if (parsedDateTime.isBefore(LocalDateTime.now())) {
                throw new InvalidDataException("Error when entering the play: the 'datePlay' field cannot be in the past!");
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDataException("Error when entering the play: invalid date format for 'datePlay' field!");
        }

    }

}
