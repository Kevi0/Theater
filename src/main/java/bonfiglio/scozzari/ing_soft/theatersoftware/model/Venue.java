package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Venue extends BaseEntityAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String venue;

    @Column
    private String cap;

    @Column
    private String city;

    @Column
    private Long capacity;

    @ManyToMany(mappedBy = "venues", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Theater> theaters = new HashSet<>();

    @OneToOne(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Opera opera;
}