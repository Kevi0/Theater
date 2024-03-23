package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.enumeration.RegexPatterns;
import org.springframework.stereotype.Component;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

@Component
public class TheaterRegistrationValidator {

    public void validate(Theater theater) throws InvalidDataException {

        validateName(theater.getName());
        validateCity(theater.getCity());
        validateTel(theater.getTel());
        validateEmail(theater.getEmail());
        validateIva(theater.getIva());
        //validatePec(theater.getPec());
        validateWebsite(theater.getWebsite());
        validateUniqueCode(theater.getUniqueCode());
        validateRecipientCode(theater.getRecipientCode());

    }

    private void validateName(String name) throws InvalidDataException {

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'name' field cannot be empty, or cannot contain only spaces!");//fatto
        }
    }

    private void validateCity(String city) throws InvalidDataException {

        if (city == null || city.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'city' field cannot be empty, or cannot contain only spaces!");//fatto
        }
        if (!city.matches(RegexPatterns.CITY_NAME_PATTERN)) {
            throw new InvalidDataException("Error during registration: the 'city' field is not in the correct format!"); //fatto
        }
    }

    private void validateTel(String tel) throws InvalidDataException {

        if (tel == null || tel.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'tel' field cannot be empty, or cannot contain only spaces!");
        }
    }

    /*public String validateTel(String tel) throws InvalidDataException {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try {
            Phonenumber.PhoneNumber parsedNumber = phoneUtil.parse(tel, null);

            if (!phoneUtil.isValidNumber(parsedNumber)) {
                throw new InvalidDataException("Error during validation: the 'tel' field is not valid!");
            }
            else {
                return phoneUtil.format(parsedNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            }
        } catch (Exception e) {
            throw new InvalidDataException("Error during registration: the 'tel' field is not in the correct format!");
        }
    }*/

    private void validateEmail(String email) throws InvalidDataException {

        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'email' field cannot be empty, or cannot contain only spaces!");
        }
        if (!email.matches(RegexPatterns.EMAIL_PATTERN)) {
            throw new InvalidDataException("Error during registration: the 'email' field is not in the correct format!");
        }
    }

    /*private void validatePec(String pec) throws InvalidDataException {

        if (pec == null || pec.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'pec' field cannot be empty, or cannot contain only spaces!");
        }
        if (!pec.matches(RegexPatterns.EMAIL_PATTERN)) {
            throw new InvalidDataException("Error during registration: the 'pec' field is not in the correct format!");
        }
    }*/

    private void validateIva(String iva) throws InvalidDataException {

        if (iva == null || iva.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'iva' field cannot be empty, or cannot contain only spaces!");
        }
        if (!iva.matches(RegexPatterns.IVA_PATTERN)) {
            throw new InvalidDataException("Error during registration: the 'iva' field is not in the correct format!");
        }
    }

    private void validateWebsite(String website) throws InvalidDataException {

        if (website == null || website.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'website' field cannot be empty, or cannot contain only spaces!");
        }
        if (!website.matches(RegexPatterns.DOMAIN_NAME_PATTERN)) {
            throw new InvalidDataException("Error during registration: the 'website' field is not in the correct format!");
        }
    }

    private void validateUniqueCode(String uniqueCode) throws InvalidDataException {

        if (uniqueCode == null || uniqueCode.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'uniqueCode' field cannot be empty, or cannot contain only spaces!");
        }
        if (!uniqueCode.matches(RegexPatterns.UNIQUE_CODE_PATTERN)) {
            throw new InvalidDataException("Error during registration: the 'uniqueCode' field is not in the correct format!");
        }
    }

    private void validateRecipientCode(String recipientCode) throws InvalidDataException {

        if (recipientCode == null || recipientCode.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'recipientCode' field cannot be empty, or cannot contain only spaces!");
        }
        if (!recipientCode.matches(RegexPatterns.RECIPIENT_CODE_PATTERN)) {
            throw new InvalidDataException("Error during registration: the 'recipientCode' field is not in the correct format!");
        }
    }

}
