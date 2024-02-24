package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.enumeration.RegexPatterns;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserRegistrationValidator {

    public void validate(User user) throws InvalidDataException {

        validateName(user.getName());
        validateSurname(user.getSurname());
        validateEmail(user.getEmail());
        validatePassword(user.getName(), user.getSurname(), user.getPassword());

    }

    private void validateName(String name) throws InvalidDataException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' non può essere vuoto!");
        }

        if (!name.matches(RegexPatterns.NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' deve avere una lunghezza compresa tra 3 e 30 caratteri, il primo carattere deve essere MAIUSCOLO e non può contenere numeri e/o caratteri speciali!");
        }

    }

    private void validateSurname(String surname) throws InvalidDataException {

        if (surname == null || surname.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' non può essere vuoto!");
        }

        if (!surname.matches(RegexPatterns.NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' deve avere una lunghezza compresa tra 3 e 30 caratteri, il primo carattere deve essere MAIUSCOLO e non può contenere numeri e/o caratteri speciali!");
        }

    }

    private void validateEmail(String email) throws InvalidDataException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'email' non può essere vuoto!");
        }
        if (!email.matches(RegexPatterns.EMAIL_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'email' non è nel formato corretto!");
        }
    }

    private void validatePassword(String name, String surname, String password) throws InvalidDataException {

        if (password == null || password.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: la password non può essere vuota!");
        }

        if (!password.matches(RegexPatterns.PASSWORD_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: la password deve seguire i criteri!");
        }

        if (password.matches(RegexPatterns.COMMON_PASSWORDS_PATTERN + Pattern.quote(name) + "|" + Pattern.quote(surname) + ").*")) {
            throw new InvalidDataException("Errore durante la registrazione: la password non può contenere sequenze comuni o informazioni personali!");
        }

    }

}
