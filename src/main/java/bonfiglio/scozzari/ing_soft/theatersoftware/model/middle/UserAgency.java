package bonfiglio.scozzari.ing_soft.theatersoftware.model.middle;

import bonfiglio.scozzari.ing_soft.theatersoftware.model.Agency;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.User;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.AgencyRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAgency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgencyRoles role;

    @ManyToOne
    private User user;

    @ManyToOne
    private Agency agency;
}
