package bonfiglio.scozzari.ing_soft.theatersoftware.model.middle;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import jakarta.persistence.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.TheaterRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTheater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Theater theater;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TheaterRoles role;

}
