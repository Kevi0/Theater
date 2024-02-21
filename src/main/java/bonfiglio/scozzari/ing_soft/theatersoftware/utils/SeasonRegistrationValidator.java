package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import bonfiglio.scozzari.ing_soft.theatersoftware.exception.customExceptions.InvalidDataException;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Season;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SeasonRegistrationValidator {

    public void validate(Season season) throws InvalidDataException {

        validateTitle(season.getTitle());
        validateStartDate(String.valueOf(season.getStartDate()));
        validateEndDate(String.valueOf(season.getEndDate()));
        validateArtisticDirectorSocialCosts(season.getArtisticDirectorSocialCosts());
        validateArtisticDirectorCompensation(season.getArtisticDirectorCompensation());
        validateArtisticPersonnelGrossSalary(season.getArtisticPersonnelGrossSalary());
        validateArtisticPersonnelSocialCosts(season.getArtisticPersonnelSocialCosts());
        validateTechnicalPersonnelGrossSalary(season.getTechnicalPersonnelGrossSalary());
        validateTechnicalPersonnelSocialCosts(season.getTechnicalPersonnelSocialCosts());
        validateAdministrativePersonnelGrossSalary(season.getAdministrativePersonnelGrossSalary());
        validateAdministrativePersonnelSocialCosts(season.getAdministrativePersonnelSocialCosts());
        validateArtisticPersonnelPerDiem(season.getArtisticPersonnelPerDiem());
        validateTechnicalPersonnelPerDiem(season.getTechnicalPersonnelPerDiem());
        validateProjectRelatedDailyExpenses(season.getProjectRelatedDailyExpenses());
        validateTravelTransportAccommodationCosts(season.getTravelTransportAccommodationCosts());

    }

    private void validateTitle(String title) throws InvalidDataException {

        if (title == null || title.trim().isEmpty()) {
            throw new InvalidDataException("Error when entering the season: the 'title' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateStartDate(String startDate) throws InvalidDataException {

        if (startDate == null || startDate.trim().isEmpty()) {
            throw new InvalidDataException("Error when entering the season: the 'startDate' field cannot be empty, or cannot contain only spaces!");
        }

        // TODO CONTROLLI SULLE DATE

    }

    private void validateEndDate(String endDate) throws InvalidDataException {

        if (endDate == null || endDate.trim().isEmpty()) {
            throw new InvalidDataException("Error when entering the season: the 'endDate' field cannot be empty, or cannot contain only spaces!");
        }

        // TODO CONTROLLI SULLE DATE

    }

    private void validateArtisticDirectorSocialCosts(BigDecimal artisticDirectorSocialCosts) throws InvalidDataException {

        if (artisticDirectorSocialCosts == null || artisticDirectorSocialCosts.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'artisticDirectorSocialCosts' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateArtisticDirectorCompensation(BigDecimal artisticDirectorCompensation) throws InvalidDataException {

        if (artisticDirectorCompensation == null || artisticDirectorCompensation.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'artisticDirectorCompensation' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateArtisticPersonnelGrossSalary(BigDecimal artisticPersonnelGrossSalary) throws InvalidDataException {

        if (artisticPersonnelGrossSalary == null || artisticPersonnelGrossSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'artisticPersonnelGrossSalary' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateArtisticPersonnelSocialCosts(BigDecimal artisticPersonnelSocialCosts) throws InvalidDataException {

        if (artisticPersonnelSocialCosts == null || artisticPersonnelSocialCosts.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'artisticPersonnelSocialCosts' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateTechnicalPersonnelGrossSalary(BigDecimal technicalPersonnelGrossSalary) throws InvalidDataException {

        if (technicalPersonnelGrossSalary == null || technicalPersonnelGrossSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'technicalPersonnelGrossSalary' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateTechnicalPersonnelSocialCosts(BigDecimal technicalPersonnelSocialCosts) throws InvalidDataException {

        if (technicalPersonnelSocialCosts == null || technicalPersonnelSocialCosts.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'technicalPersonnelSocialCosts' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateAdministrativePersonnelGrossSalary(BigDecimal administrativePersonnelGrossSalary) throws InvalidDataException {

        if (administrativePersonnelGrossSalary == null || administrativePersonnelGrossSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'administrativePersonnelGrossSalary' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateAdministrativePersonnelSocialCosts(BigDecimal administrativePersonnelSocialCosts) throws InvalidDataException {

        if (administrativePersonnelSocialCosts == null || administrativePersonnelSocialCosts.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'administrativePersonnelSocialCosts' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateArtisticPersonnelPerDiem(BigDecimal artisticPersonnelPerDiem) throws InvalidDataException {

        if (artisticPersonnelPerDiem == null || artisticPersonnelPerDiem.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'artisticPersonnelPerDiem' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateTechnicalPersonnelPerDiem(BigDecimal technicalPersonnelPerDiem) throws InvalidDataException {

        if (technicalPersonnelPerDiem == null || technicalPersonnelPerDiem.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'technicalPersonnelPerDiem' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateProjectRelatedDailyExpenses(BigDecimal projectRelatedDailyExpenses) throws InvalidDataException {

        if (projectRelatedDailyExpenses == null || projectRelatedDailyExpenses.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'projectRelatedDailyExpenses' field cannot be empty, or cannot contain only spaces!");
        }

    }

    private void validateTravelTransportAccommodationCosts(BigDecimal travelTransportAccommodationCosts) throws InvalidDataException {

        if (travelTransportAccommodationCosts == null || travelTransportAccommodationCosts.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataException("Error when entering the season: the 'travelTransportAccommodationCosts' field cannot be empty, or cannot contain only spaces!");
        }

    }

}
