package bonfiglio.scozzari.ing_soft.theatersoftware.models;

import bonfiglio.scozzari.ing_soft.theatersoftware.enums.Gender;
import bonfiglio.scozzari.ing_soft.theatersoftware.enums.Occupation;
import bonfiglio.scozzari.ing_soft.theatersoftware.enums.StateOfCitizenship;
import bonfiglio.scozzari.ing_soft.theatersoftware.enums.Taxation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private LocalDate birthDate;

    @Column
    private String birthPlace;

    @Column
    private String birthState;

    @Column
    private String educationTitle;

    @Column
    private Boolean isEuropean;

    @Column
    @Enumerated(EnumType.STRING)
    private StateOfCitizenship stateOfCitizenship;

    @Column
    private String cellPhone1;

    @Column
    private String cellPhone2;

    @Column
    private String pec;

    @Column
    private String website;

    @Column
    private String residence;

    @Column
    private String postalCodeOfResidence;

    @Column
    private String cityOfResidence;

    @Column
    private String provinceOfResidence;

    @Column
    private String regionOfResidence;

    @Column
    private String stateOfResidence;

    @Column
    private String domicile;

    @Column
    private String postalCodeOfDomicile;

    @Column
    private String cityOfDomicile;

    @Column
    private String provinceOfDomicile;

    @Column
    private String regionOfDomicile;

    @Column
    private String stateOfDomicile;

    @Column
    private String stageName;

    @Column
    private String instrument;

    @Column
    private String voice;

    @Column
    private Boolean isIva;

    @Column
    private String iva;

    @Column
    @Enumerated(EnumType.STRING)
    private Taxation taxation;

    @Column
    private String inpsNumber;

    @Column
    private Boolean memberFrom;

    @Column
    @Enumerated(EnumType.STRING)
    private Occupation occupation;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Retired retired;

    @OneToOne(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TemporaryWork temporaryWork;

    @OneToOne(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PermanentWork permanentWork;

    @OneToOne(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Unemployed unemployed;

    @OneToOne(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BankAccountIT bankAccountIT;

    @OneToOne(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BankAccountES bankAccountES;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "artist_typology",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "typology_id") }
    )
    private Set<Typology> typologies = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "artist_opera",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "opera_id") }
    )
    private Set<Opera> operas = new HashSet<>();

    @OneToOne(mappedBy = "artist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DigitalDrawer digitalDrawer;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "artist_agency",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "agency_id") }
    )
    private Set<Agency> agencies = new HashSet<>();

}
