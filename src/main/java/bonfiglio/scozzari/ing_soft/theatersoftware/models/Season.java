package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private BigDecimal artisticDirectorSocialCosts;

    @Column
    private BigDecimal artisticDirectorCompensation;

    @Column
    private BigDecimal artisticPersonnelGrossSalary;

    @Column
    private BigDecimal artisticPersonnelSocialCosts;

    @Column
    private BigDecimal technicalPersonnelGrossSalary;

    @Column
    private BigDecimal technicalPersonnelSocialCosts;

    @Column
    private BigDecimal administrativePersonnelGrossSalary;

    @Column
    private BigDecimal administrativePersonnelSocialCosts;

    @Column
    private BigDecimal artisticPersonnelPerDiem;

    @Column
    private BigDecimal technicalPersonnelPerDiem;

    @Column
    private BigDecimal projectRelatedDailyExpenses;

    @Column
    private BigDecimal travelTransportAccommodationCosts;
}
