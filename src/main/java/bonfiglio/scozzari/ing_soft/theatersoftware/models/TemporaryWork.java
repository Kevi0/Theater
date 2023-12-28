package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntity;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntityAudit;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.interfaces.Work;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryWork implements Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate deadline;

    @Column
    private String employer;

    @Column
    private Boolean isRevenueOver5000;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Artist artist;
}
