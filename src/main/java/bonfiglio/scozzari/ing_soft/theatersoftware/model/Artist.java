package bonfiglio.scozzari.ing_soft.theatersoftware.model;

import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.Gender;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.Occupation;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.StateOfCitizenship;
import bonfiglio.scozzari.ing_soft.theatersoftware.enumaration.Taxation;
import bonfiglio.scozzari.ing_soft.theatersoftware.model.audit.BaseEntityAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist extends BaseEntityAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String taxCode;

    @Column(nullable = false)
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

    @OneToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "retired_id")
    private Retired retired;

    @ManyToOne
    @JoinColumn(name = "temporary_work_id")
    private TemporaryWork temporaryWork;

    @ManyToOne
    @JoinColumn(name = "permanent_work_id")
    private PermanentWork permanentWork;

    @ManyToOne
    @JoinColumn(name = "unemployed_id")
    private Unemployed unemployed;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "bank_account_it_id")
    private BankAccountIT bankAccountIT;

    @ManyToOne
    @JoinColumn(name = "bank_account_es_id")
    private BankAccountES bankAccountES;

    @ManyToMany
    @JoinTable(
            name = "artist_agency",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "agency_id") }
    )
    private Set<Agency> agencies = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "artist_typology",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "typology_id") }
    )
    private Set<Typology> typologies = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "artist_opera",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "opera_id") }
    )
    private Set<Opera> operas = new HashSet<>();

    @OneToOne(mappedBy = "artist") // mappedBy = "artist" -> artist è il nome del campo nella classe DigitalDrawer
    private DigitalDrawer digitalDrawer;

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, taxCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id) && Objects.equals(taxCode, artist.taxCode);
    }
}
