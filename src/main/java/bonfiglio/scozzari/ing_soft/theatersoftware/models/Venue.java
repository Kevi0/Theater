package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {

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
}