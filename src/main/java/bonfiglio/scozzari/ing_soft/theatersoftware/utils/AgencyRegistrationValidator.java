package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.enumeration.RegexPatterns;
import org.springframework.stereotype.Component;

@Component
public class AgencyRegistrationValidator {

    public void validate(Agency agency) throws InvalidDataException {

        validateName(agency.getName());
        validateEmail(agency.getEmail());
        validatePec(agency.getPec());
        validateTel1(agency.getTel1());
        /*validateTel2(agencyDTO.getTel2());
        validateWebsite(agencyDTO.getWebsite());*/

    }

    private void validateName(String name) throws InvalidDataException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'nome' non può essere vuoto, o non può contenere solo spazi!");
        }

    }

    private void validateEmail(String email) throws InvalidDataException {

        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'email' non può essere vuoto, o non può contenere solo spazi!");
        }
        if (!email.matches(RegexPatterns.EMAIL_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'email' non è nel formato corretto!");
        }
    }

    private void validatePec(String pec) throws InvalidDataException {

        if (pec == null || pec.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'pec' non può essere vuoto!");
        }
        if (!pec.matches(RegexPatterns.EMAIL_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'pec' non è nel formato corretto!");
        }
    }

    private void validateTel1(String tel1) throws InvalidDataException {

        if (tel1 == null || tel1.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'tel1' non può essere vuoto!");
        }
        if (!tel1.matches(RegexPatterns.TEL_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'tel1' non è nel formato corretto!");
        }
    }

    /*private void validateTel2(String tel2) throws InvalidDataException {
        if (tel2.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'tel2' non può contenere solo spazi vuoti!");
        }
    }*/

    private void validateWebsite(String website) throws InvalidDataException {

        if (!website.matches(RegexPatterns.DOMAIN_NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'website' non è nel formato corretto!");
        }
    }

}
