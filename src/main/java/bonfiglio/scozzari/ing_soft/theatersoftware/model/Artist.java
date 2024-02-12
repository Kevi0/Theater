package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.Gender;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.Occupation;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.StateOfCitizenship;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.Taxation;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist extends BaseEntityAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String birthPlace;

    @Column(nullable = false)
    private String birthState;

    @Column(nullable = false)
    private String educationTitle;

    @Column(nullable = false)
    private Boolean isEuropean;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StateOfCitizenship stateOfCitizenship;

    @Column(nullable = false)
    private String cellPhone1;

    @Column
    private String cellPhone2;

    @Column
    private String pec;

    @Column
    private String website;

    @Column(nullable = false)
    private String residence;

    @Column(nullable = false)
    private String postalCodeOfResidence;

    @Column(nullable = false)
    private String cityOfResidence;

    @Column(nullable = false)
    private String provinceOfResidence;

    @Column(nullable = false)
    private String regionOfResidence;

    @Column(nullable = false)
    private String stateOfResidence;

    @Column(nullable = false)
    private String domicile;

    @Column(nullable = false)
    private String postalCodeOfDomicile;

    @Column(nullable = false)
    private String cityOfDomicile;

    @Column(nullable = false)
    private String provinceOfDomicile;

    @Column(nullable = false)
    private String regionOfDomicile;

    @Column(nullable = false)
    private String stateOfDomicile;

    @Column
    private String stageName;

    @Column
    private String instrument;

    @Column
    private String voice;

    @Column
    private Boolean isIva;

    @Column(nullable = false)
    private String iva;

    @Column
    @Enumerated(EnumType.STRING)
    private Taxation taxation;

    @Column(nullable = false)
    private String inpsNumber;

    @Column(nullable = false)
    private Boolean memberFrom;

    @Column(nullable = false)
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
