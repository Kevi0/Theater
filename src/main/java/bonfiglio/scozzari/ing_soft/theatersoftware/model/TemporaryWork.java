package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(nullable = false)
    private String employer;

    @Column(nullable = false)
    private Boolean isRevenueOver5000;

    @OneToMany(mappedBy = "temporaryWork") // mappedBy = "temporaryWork" è il nome del campo nella classe Artist
    private Set<Artist> artists= new HashSet<>();
}
