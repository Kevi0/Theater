package bonfiglio.scozzari.ing_soft.theatersoftware.dto.inputDTO;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeasonDTO implements InputDTO {

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Start date cannot be null")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @NotBlank(message = "End date cannot be null")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    private Long idTheater;

    @NotBlank(message = "Artistic Director Social Costs cannot be null")
    @DecimalMin(value = "0.00", message = "Artistic Director Social Costs must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Artistic Director Social Costs must be <= 9999999.99")
    private BigDecimal artisticDirectorSocialCosts;

    @NotBlank(message = "Artistic Director Compensation cannot be null")
    @DecimalMin(value = "0.00", message = "Artistic Director Compensation must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Artistic Director Compensation must be <= 9999999.99")
    private BigDecimal artisticDirectorCompensation;

    @NotBlank(message = "Artistic Personnel Gross Salary cannot be null")
    @DecimalMin(value = "0.00", message = "Artistic Personnel Gross Salary must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Artistic Personnel Gross Salary must be <= 9999999.99")
    private BigDecimal artisticPersonnelGrossSalary;

    @NotBlank(message = "Artistic Personnel Social Costs cannot be null")
    @DecimalMin(value = "0.00", message = "Artistic Personnel Social Costs must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Artistic Personnel Social Costs must be <= 9999999.99")
    private BigDecimal artisticPersonnelSocialCosts;

    @NotBlank(message = "Technical Personnel Gross Salary cannot be null")
    @DecimalMin(value = "0.00", message = "Technical Personnel Gross Salary must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Technical Personnel Gross Salary must be <= 9999999.99")
    private BigDecimal technicalPersonnelGrossSalary;

    @NotBlank(message = "Technical Personnel Social Costs cannot be null")
    @DecimalMin(value = "0.00", message = "Technical Personnel Social Costs must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Technical Personnel Social Costs must be <= 9999999.99")
    private BigDecimal technicalPersonnelSocialCosts;

    @NotBlank(message = "Administrative Personnel Gross Salary cannot be null")
    @DecimalMin(value = "0.00", message = "Administrative Personnel Gross Salary must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Administrative Personnel Gross Salary must be <= 9999999.99")
    private BigDecimal administrativePersonnelGrossSalary;

    @NotBlank(message = "Administrative Personnel Social Costs cannot be null")
    @DecimalMin(value = "0.00", message = "Administrative Personnel Social Costs must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Administrative Personnel Social Costs must be <= 9999999.99")
    private BigDecimal administrativePersonnelSocialCosts;

    @NotBlank(message = "Artistic Personnel Per Diem cannot be null")
    @DecimalMin(value = "0.00", message = "Artistic Personnel Per Diem must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Artistic Personnel Per Diem must be <= 9999999.99")
    private BigDecimal artisticPersonnelPerDiem;

    @NotBlank(message = "Technical Personnel Per Diem cannot be null")
    @DecimalMin(value = "0.00", message = "Technical Personnel Per Diem must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Technical Personnel Per Diem must be <= 9999999.99")
    private BigDecimal technicalPersonnelPerDiem;

    @NotBlank(message = "Project Related Daily Expenses cannot be null")
    @DecimalMin(value = "0.00", message = "Project Related Daily Expenses must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Project Related Daily Expenses must be <= 9999999.99")
    private BigDecimal projectRelatedDailyExpenses;

    @NotBlank(message = "Travel Transport Accommodation Costs cannot be null")
    @DecimalMin(value = "0.00", message = "Travel Transport Accommodation Costs must be >= 0.00")
    @DecimalMax(value = "9999999.99", message = "Travel Transport Accommodation Costs must be <= 9999999.99")
    private BigDecimal travelTransportAccommodationCosts;
}
