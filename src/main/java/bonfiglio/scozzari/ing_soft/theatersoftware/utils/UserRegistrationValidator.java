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
        validateTaxCode(user.getTaxCode());
        validateEmail(user.getEmail());
        validateUsername(user.getName(), user.getSurname(), user.getUsername());
        validatePassword(user.getName(), user.getSurname(), user.getPassword());

    }

    private void validateName(String name) throws InvalidDataException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' non può essere vuoto!");
        }

        if (!name.matches(RegexPatterns.LENGTH_NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' deve avere una lunghezza compresa tra 3 e 40 caratteri!");
        }

        if (!name.matches(RegexPatterns.FORMAT_NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' deve iniziare con una lettera maiuscola seguita da lettere minuscole!");
        }

        if (name.matches(RegexPatterns.NOT_NUMBERS_NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' non può contenere numeri!");
        }

        if (name.matches(RegexPatterns.NOT_SPECIAL_CHARACTERS_NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' non può contenere caratteri speciali!");
        }

    }

    private void validateSurname(String surname) throws InvalidDataException {

        if (surname == null || surname.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' non può essere vuoto!");
        }

        if (!surname.matches(RegexPatterns.LENGTH_NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' deve avere una lunghezza compresa tra 3 e 50 caratteri!");
        }

        if (!surname.matches(RegexPatterns.FORMAT_NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' deve iniziare con una lettera maiuscola seguita da lettere minuscole!");
        }

        if (surname.matches(RegexPatterns.NOT_NUMBERS_NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' non può contenere numeri!");
        }

        if (surname.matches(RegexPatterns.NOT_SPECIAL_CHARACTERS_NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' non può contenere caratteri speciali!");
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

    private void validateTaxCode(String taxCode) throws InvalidDataException {

        if (taxCode == null || taxCode.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'codiceFiscale' non può essere vuoto!");
        }

        if (!taxCode.matches(RegexPatterns.TAX_CODE_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'codiceFiscale' non è valido!");
        }

    }

    private void validateUsername(String name, String surname, String username) throws InvalidDataException {

        if (username == null || username.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'username' non può essere vuoto!");
        }

        if (!(username.equals(name.trim().toLowerCase() + "." + surname.trim().toLowerCase()))) {
            throw new InvalidDataException("Errore durante la registrazione: l'username non segue la notazione 'nome.cognome'!");
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
