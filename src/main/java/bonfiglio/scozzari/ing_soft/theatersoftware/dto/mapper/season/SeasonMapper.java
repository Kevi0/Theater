package bonfiglio.scozzari.ing_soft.theatersoftware.dto.mapper.season;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.season.SeasonDTO;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Season;
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
