package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Season extends BaseEntityAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private BigDecimal artisticDirectorSocialCosts;

    @Column(nullable = false)
    private BigDecimal artisticDirectorCompensation;

    @Column(nullable = false)
    private BigDecimal artisticPersonnelGrossSalary;

    @Column(nullable = false)
    private BigDecimal artisticPersonnelSocialCosts;

    @Column(nullable = false)
    private BigDecimal technicalPersonnelGrossSalary;

    @Column(nullable = false)
    private BigDecimal technicalPersonnelSocialCosts;

    @Column(nullable = false)
    private BigDecimal administrativePersonnelGrossSalary;

    @Column(nullable = false)
    private BigDecimal administrativePersonnelSocialCosts;

    @Column(nullable = false)
    private BigDecimal artisticPersonnelPerDiem;

    @Column(nullable = false)
    private BigDecimal technicalPersonnelPerDiem;

    @Column(nullable = false)
    private BigDecimal projectRelatedDailyExpenses;

    @Column(nullable = false)
    private BigDecimal travelTransportAccommodationCosts;

    @ManyToMany(mappedBy = "seasons",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Opera> operas = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "theater_id")
    private Theater theater;
}