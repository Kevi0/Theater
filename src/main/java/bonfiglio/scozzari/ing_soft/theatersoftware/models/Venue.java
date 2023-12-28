package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntity;
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
public class Venue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String venue;

    @Column
    private String cap;

    @Column
    private String city;

    @Column
    private Long capacity;

    @ManyToMany(mappedBy = "venues", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Theater> theaters = new HashSet<>();

    @OneToOne(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Opera opera;
}