package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.models.middleTables.UserAgency;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agency{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String pec;

    @Column
    private String tel1;

    @Column
    private String tel2;

    @Column
    private String webSite;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserAgency> userAgencies = new HashSet<>();

    @ManyToMany(mappedBy = "agency", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Artist> artists = new HashSet<>();
}