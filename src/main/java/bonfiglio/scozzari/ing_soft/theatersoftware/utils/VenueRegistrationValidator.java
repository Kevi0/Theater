package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Venue;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.enumeration.RegexPatterns;
import org.springframework.stereotype.Component;

@Component
public class VenueRegistrationValidator {
    public void validate(Venue venue) throws InvalidDataException {
        validateName(venue.getName());
        validateAddress(venue.getVenue());
        validateCap(venue.getCap());
        validateCity(venue.getCity());
        validateCap(venue.getCap());
    }

    private void validateName(String name) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'name' field cannot be empty, or cannot contain only spaces!");
        }
    }

    private void validateCity(String city) throws InvalidDataException {
        if (city == null || city.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'city' field cannot be empty, or cannot contain only spaces!");
        }
        if (!city.matches(RegexPatterns.CITY_NAME_PATTERN)) {
            throw new InvalidDataException("Error during registration: the 'city' field is not in the correct format!");
        }
    }

    private void validateAddress(String address) throws InvalidDataException {
        if (address == null || address.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'address' field cannot be empty, or cannot contain only spaces!");
        }
    }

    private void validateCap(String cap) throws InvalidDataException {
        if (cap == null || cap.trim().isEmpty()) {
            throw new InvalidDataException("Error during registration: the 'cap' field cannot be empty, or cannot contain only spaces!");
        }
    }
}
