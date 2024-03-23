package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

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

    @ManyToMany(mappedBy = "seasons")
    private Set<Opera> operas = new HashSet<>();

    @ManyToOne
    private Theater theater;

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title, startDate, endDate, artisticDirectorSocialCosts, artisticDirectorCompensation, artisticPersonnelGrossSalary, artisticPersonnelSocialCosts, technicalPersonnelGrossSalary, technicalPersonnelSocialCosts, administrativePersonnelGrossSalary, administrativePersonnelSocialCosts, artisticPersonnelPerDiem, technicalPersonnelPerDiem, projectRelatedDailyExpenses, travelTransportAccommodationCosts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Season season = (Season) o;
        return Objects.equals(id, season.id) && Objects.equals(title, season.title) && Objects.equals(startDate, season.startDate) && Objects.equals(endDate, season.endDate) && Objects.equals(artisticDirectorSocialCosts, season.artisticDirectorSocialCosts) && Objects.equals(artisticDirectorCompensation, season.artisticDirectorCompensation) && Objects.equals(artisticPersonnelGrossSalary, season.artisticPersonnelGrossSalary) && Objects.equals(artisticPersonnelSocialCosts, season.artisticPersonnelSocialCosts) && Objects.equals(technicalPersonnelGrossSalary, season.technicalPersonnelGrossSalary) && Objects.equals(technicalPersonnelSocialCosts, season.technicalPersonnelSocialCosts) && Objects.equals(administrativePersonnelGrossSalary, season.administrativePersonnelGrossSalary) && Objects.equals(administrativePersonnelSocialCosts, season.administrativePersonnelSocialCosts) && Objects.equals(artisticPersonnelPerDiem, season.artisticPersonnelPerDiem) && Objects.equals(technicalPersonnelPerDiem, season.technicalPersonnelPerDiem) && Objects.equals(projectRelatedDailyExpenses, season.projectRelatedDailyExpenses) && Objects.equals(travelTransportAccommodationCosts, season.travelTransportAccommodationCosts);
    }

}