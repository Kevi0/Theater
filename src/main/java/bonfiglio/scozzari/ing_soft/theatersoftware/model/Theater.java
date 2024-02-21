package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserTheater;
import bonfiglio.scozzari.ing_soft.theatersoftware.observer.Observers;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.Updatable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theater extends BaseEntityAudit implements Observers, Updatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String email;

    @Column
    private String pec;

    @Column
    private String website;

    @Column(nullable = false)
    private String iva;

    @Column(nullable = false)
    private String uniqueCode;

    @Column(nullable = false)
    private String recipientCode;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserTheater> userTheaters = new HashSet<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Season> seasons = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "theater_venue",
            joinColumns = @JoinColumn(name = "theater_id"),
            inverseJoinColumns = @JoinColumn(name = "venue_id")
    )
    private Set<Venue> venues = new HashSet<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DigitalDrawer> digitalDrawers = new HashSet<>();


    @Override
    public void update() {

    }

    @Override
    public void setUpdateAt(LocalDateTime updateAt) {
        this.setUpdatedAt(updateAt);
    }
}
