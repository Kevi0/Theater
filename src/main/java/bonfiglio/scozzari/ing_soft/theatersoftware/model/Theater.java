package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.middle.UserTheater;
import bonfiglio.scozzari.ing_soft.theatersoftware.observer.Observers;
import bonfiglio.scozzari.ing_soft.theatersoftware.utils.Updatable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, unique = true)
    private String tel;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true, unique = true)
    private String pec;

    @Column(nullable = true)
    private String website;

    @Column(nullable = false, unique = true, updatable = false)
    private String iva;

    @Column(nullable = false, unique = true)
    //TODO CHECK UNIQUE
    private String uniqueCode;

    @Column(nullable = false, unique = true)
    //TODO CHECK UNIQUE
    private String recipientCode;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)// mappedBy = "theater" -> theater è il nome del campo nella classe UserTheater
    private Set<UserTheater> userTheaters = new HashSet<>();

    @OneToMany(mappedBy = "theater") // mappedBy = "theater" -> theater è il nome del campo nella classe Season
    private Set<Season> seasons = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "theater_venue",
            joinColumns = @JoinColumn(name = "theater_id"),
            inverseJoinColumns = @JoinColumn(name = "venue_id")
    )
    private Set<Venue> venues = new HashSet<>();

    @OneToMany(mappedBy = "theater") // mappedBy = "theater" -> theater è il nome del campo nella classe DigitalDrawer
    private Set<DigitalDrawer> digitalDrawers = new HashSet<>();

    @Override
    public void update() {

    }

    @Override
    public void setUpdateAt(LocalDateTime updateAt) {
        this.setUpdatedAt(updateAt);
    }

}
