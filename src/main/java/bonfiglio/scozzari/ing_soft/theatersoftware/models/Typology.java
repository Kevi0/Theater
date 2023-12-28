package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntity;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.audit.BaseEntityAudit;
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
public class Typology extends BaseEntityAudit {

    @Id
    @Column(length = 3)
    private String id;

    @Column
    private String nameTypology;

    @ManyToMany(mappedBy = "typologies", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Artist> artists = new HashSet<>();
}