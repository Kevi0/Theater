package bonfiglio.scozzari.ing_soft.theatersoftware.models.middleTables;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.User;
import jakarta.persistence.*;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.Theater;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.enums.TheaterRoles;
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
