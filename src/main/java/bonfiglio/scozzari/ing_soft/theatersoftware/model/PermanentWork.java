package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.interfaces.Work;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermanentWork implements Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employer;

    @Column(nullable = false)
    private Boolean isRevenueOver5000;

    @OneToMany(mappedBy = "permanentWork") // mappedBy = "permanentWork" è il nome del campo nella classe Artist
    private Set<Artist> artists= new HashSet<>();
}
