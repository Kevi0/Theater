package bonfiglio.scozzari.ing_soft.theatersoftware.model.middle;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import jakarta.persistence.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.TheaterRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTheater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Theater theater;

    @Column
    private TheaterRoles role;

}
