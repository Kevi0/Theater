package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Retired {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Boolean isOver65;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Artist artist;
}
