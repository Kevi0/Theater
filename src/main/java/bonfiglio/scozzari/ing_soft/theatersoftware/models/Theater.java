package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.middleTables.UserTheater;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.observer.Observers;
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
public class Theater implements Observers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String tel;

    @Column
    private String email;

    @Column
    private String pec;

    @Column
    private String taxCode;

    @Column
    private String webSite;

    @Column
    private String iva;

    @Column
    private String uniqueCode;

    @Column
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
}
