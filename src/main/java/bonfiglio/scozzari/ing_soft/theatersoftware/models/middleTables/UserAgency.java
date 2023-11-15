package bonfiglio.scozzari.ing_soft.theatersoftware.models.middleTables;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.models.enums.AgencyRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAgency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private AgencyRoles role;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Agency agency;
}
