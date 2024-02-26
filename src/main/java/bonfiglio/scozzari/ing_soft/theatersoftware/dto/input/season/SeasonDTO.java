package bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.season;

import bonfiglio.scozzari.ing_soft.theatersoftware.dto.input.InputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonDTO implements InputDTO {

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal artisticDirectorSocialCosts;

    private BigDecimal artisticDirectorCompensation;

    private BigDecimal artisticPersonnelGrossSalary;

    private BigDecimal artisticPersonnelSocialCosts;

    private BigDecimal technicalPersonnelGrossSalary;

    private BigDecimal technicalPersonnelSocialCosts;

    private BigDecimal administrativePersonnelGrossSalary;

    private BigDecimal administrativePersonnelSocialCosts;

    private BigDecimal artisticPersonnelPerDiem;

    private BigDecimal technicalPersonnelPerDiem;

    private BigDecimal projectRelatedDailyExpenses;

    private BigDecimal travelTransportAccommodationCosts;

    private Long idTheater;

}
