package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Opera extends BaseEntityAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate startRehearsal;

    @ManyToMany(mappedBy = "operas")
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "opera_season",
            joinColumns = @JoinColumn(name = "opera_id"),
            inverseJoinColumns = @JoinColumn(name = "season_id")
    )
    private Set<Season> seasons = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @OneToMany(mappedBy = "opera")
    private Set<Play> plays = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, title, startDate, startRehearsal, venue, plays);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Opera opera = (Opera) o;
        return Objects.equals(id, opera.id) && Objects.equals(title, opera.title) && Objects.equals(startDate, opera.startDate) && Objects.equals(startRehearsal, opera.startRehearsal) && Objects.equals(venue, opera.venue) && Objects.equals(plays, opera.plays);
    }
}
