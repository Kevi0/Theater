package bonfiglio.scozzari.ing_soft.theatersoftware.models;

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
public class DigitalDrawer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToMany(mappedBy = "digitalDrawer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Document> documents =  new HashSet<>();
}
