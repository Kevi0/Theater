package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String tel;

    @Column
    private String email;

    @Column
    private String pec;

    @Column
    private String taxCode;

    @Column
    private String webSite;

    @Column
    private String iva;

    @Column
    private String uniqueCode;

    @Column
    private String recipientCode;

}
