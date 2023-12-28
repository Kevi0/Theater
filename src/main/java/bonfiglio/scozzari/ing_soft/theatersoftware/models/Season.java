package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntity;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(mappedBy = "seasons",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Opera> operas = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "theater_id")
    private Theater theater;
}