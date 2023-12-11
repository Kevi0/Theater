package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mappers.seasonMapper;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO.registrationSeason.SeasonDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Season;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper {

    public Season seasonDTOToSeason(InputDTO seasonDTO){
        if(!(seasonDTO instanceof SeasonDTO seasonData)){
            return null;
        }

        Season season = new Season();

        season.setTitle(seasonData.getTitle());
        season.setStartDate(seasonData.getStartDate());
        season.setEndDate(seasonData.getEndDate());
        season.setArtisticDirectorSocialCosts(seasonData.getArtisticDirectorSocialCosts());
        season.setArtisticDirectorCompensation(seasonData.getArtisticDirectorCompensation());
        season.setArtisticPersonnelGrossSalary(seasonData.getArtisticPersonnelGrossSalary());
        season.setArtisticPersonnelSocialCosts(seasonData.getArtisticPersonnelSocialCosts());
        season.setTechnicalPersonnelGrossSalary(seasonData.getTechnicalPersonnelGrossSalary());
        season.setTechnicalPersonnelSocialCosts(seasonData.getTechnicalPersonnelSocialCosts());
        season.setAdministrativePersonnelGrossSalary(seasonData.getAdministrativePersonnelGrossSalary());
        season.setAdministrativePersonnelSocialCosts(seasonData.getAdministrativePersonnelSocialCosts());
        season.setArtisticPersonnelPerDiem(seasonData.getArtisticPersonnelPerDiem());
        season.setTechnicalPersonnelPerDiem(seasonData.getTechnicalPersonnelPerDiem());
        season.setProjectRelatedDailyExpenses(seasonData.getProjectRelatedDailyExpenses());
        season.setTravelTransportAccommodationCosts(seasonData.getTravelTransportAccommodationCosts());

        return season;

    }

}
