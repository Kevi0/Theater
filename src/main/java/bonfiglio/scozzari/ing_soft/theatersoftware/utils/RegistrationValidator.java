package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import bonfiglio.scozzari.ing_soft.theatersoftware.exceptions.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.security.authentication.RegisterRequest;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegistrationValidator {

    public void validate(RegisterRequest request) throws InvalidDataException {

        validateName(request.getName());
        validateSurname(request.getSurname());
        validateTaxCode(request.getTaxCode());
        validateEmail(request.getEmail());
        validateUsername(request.getName(), request.getSurname(), request.getUsername());
        validatePassword(request.getName(), request.getSurname(), request.getPassword());

    }

    private void validateName(String name) throws InvalidDataException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' non può essere vuoto!");
        }

        // Lunghezza minima e massima (3-20 caratteri)
        if (!name.matches(".{3,40}")) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' deve avere una lunghezza compresa tra 3 e 40 caratteri!");
        }

        // Deve seguire il formato specifico (prima lettera maiuscola, seguita da lettere minuscole)
        if (!name.matches("^[A-Z][a-z]*$")) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' deve iniziare con una lettera maiuscola seguita da lettere minuscole!");
        }

        // Non deve contenere numeri
        if (name.matches(".*\\d.*")) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' non può contenere numeri!");
        }

        // Non deve contenere caratteri speciali
        if (name.matches(".*[!@#\\$%\\^&\\*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'name' non può contenere caratteri speciali!");
        }

    }

    private void validateSurname(String surname) throws InvalidDataException {

        if (surname == null || surname.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' non può essere vuoto!");
        }

        // Lunghezza minima e massima (3-20 caratteri)
        if (!surname.matches(".{3,50}")) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' deve avere una lunghezza compresa tra 3 e 50 caratteri!");
        }

        // Deve seguire il formato specifico (prima lettera maiuscola, seguita da lettere minuscole)
        if (!surname.matches("^[A-Z][a-z]*$")) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' deve iniziare con una lettera maiuscola seguita da lettere minuscole!");
        }

        // Non deve contenere numeri
        if (surname.matches(".*\\d.*")) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' non può contenere numeri!");
        }

        // Non deve contenere caratteri speciali
        if (surname.matches(".*[!@#\\$%\\^&\\*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'surname' non può contenere caratteri speciali!");
        }

    }

    private void validateEmail(String email) throws InvalidDataException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'email' non può essere vuoto!");
        }
        if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'email' non è nel formato corretto!");
        }
    }

    private void validateTaxCode(String taxCode) throws InvalidDataException {

        if (taxCode == null || taxCode.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'codiceFiscale' non può essere vuoto!");
        }

        // Inserisci qui la tua regex per la validazione del codice fiscale
        String codiceFiscaleRegex = "^(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}" +
                "(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])" +
                "|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$";

        if (!taxCode.matches(codiceFiscaleRegex)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'codiceFiscale' non è valido!");
        }

    }

    private void validateUsername(String name, String surname, String username) throws InvalidDataException {

        if (username == null || username.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'username' non può essere vuoto!");
        }

        // Verifica se l'username segue la notazione "nome.cognome"
        String usernameRegex = name.trim().toLowerCase() + "." + surname.trim().toLowerCase();

        if (!(username.equals(usernameRegex))) {
            throw new InvalidDataException("Errore durante la registrazione: l'username non segue la notazione 'nome.cognome'!");
        }

    }

    private void validatePassword(String name, String surname, String password) throws InvalidDataException {

        if (password == null || password.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: la password non può essere vuota!");
        }

        // La regex fornita per la password
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*\\-._]).{8,}$";

        // Verifica se la password segue la regex
        if (!password.matches(passwordRegex)) {
            throw new InvalidDataException("Errore durante la registrazione: la password deve seguire i criteri!");
        }

        // Evita sequenze comuni o informazioni personali (es. "123", "password", nome, cognome)
        if (password.matches(".*(?i:123|password|" + Pattern.quote(name) + "|" + Pattern.quote(surname) + ").*")) {
            throw new InvalidDataException("Errore durante la registrazione: la password non può contenere sequenze comuni o informazioni personali!");
        }


    }

}
