package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Venue extends BaseEntityAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String venue;

    @Column(nullable = false)
    private String cap;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Long capacity;

    @ManyToMany(mappedBy = "venues")
    private Set<Theater> theaters = new HashSet<>();

    @OneToMany(mappedBy = "venue")
    private Set<Opera> operas = new HashSet<>();

    @OneToMany(mappedBy = "venue")
    private Set<Play> plays = new HashSet<>();
}